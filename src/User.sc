;;; Sierra Script 1.0 - (do not remove this comment)
(script# 996)
(include sci.sh)
(use Main)
(use Intrface)
(use SortCopy)
(use Sound)
(use Motion)
(use Menu)
(use Actor)
(use System)


(local
	[inputLine 23]
	inputLen
)
(class User of Obj
	(properties
		alterEgo 0
		canInput 0
		controls 0
		echo 32
		prevDir 0
		prompt {Introduce orden}
		inputLineAddr 0
		x -1
		y -1
		blocks 1
		mapKeyToDir 1
		curEvent 0
	)
	
	(method (init param1 param2)
		(= inputLineAddr (if argc param1 else @inputLine))
		(= inputLen (if (== argc 2) param2 else 45))
		(= curEvent uEvt)
	)
	
	(method (doit)
		(if (== 0 demoScripts)
			(curEvent
				type: 0
				message: 0
				modifiers: 0
				y: 0
				x: 0
				claimed: 0
			)
			(GetEvent 32767 curEvent)
			(self handleEvent: curEvent)
		)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(asm
			pushi    #type
			pushi    0
			lap      event
			send     4
			bnt      code_011f
			lap      event
			sag      lastEvent
			pushi    #type
			pushi    0
			lap      event
			send     4
			sat      temp0
			pToa     mapKeyToDir
			bnt      code_007c
			pushi    1
			lsp      event
			callk    MapKeyToDir,  2
code_007c:
			class    TheMenuBar
			bnt      code_0080
code_0080:
			pushi    1
			lsp      event
			callk    GlobalToLocal,  2
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_009b
			pushi    #handleEvent
			pushi    2
			lsp      event
			lst      temp0
			lag      theGame
			send     8
code_009b:
			pToa     controls
			bnt      code_00bd
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_00bd
			pushi    #contains
			pushi    1
			pTos     alterEgo
			lag      cast
			send     6
			bnt      code_00bd
			pushi    #handleEvent
			pushi    1
			lsp      event
			pToa     alterEgo
			send     6
code_00bd:
			pToa     canInput
			bnt      code_011f
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_011f
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			eq?     
			bnt      code_011f
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			pToa     echo
			eq?     
			bt       code_00f8
			pushi    32
			pushi    #message
			pushi    0
			lap      event
			send     4
			le?     
			bnt      code_00f6
			pprev   
			ldi      255
			le?     
code_00f6:
			bnt      code_011f
code_00f8:
			pushi    #getInput
			pushi    1
			lsp      event
			self     6
			bnt      code_011f
			pushi    2
			lea      @inputLine
			push    
			lsp      event
			callk    Parse,  4
			bnt      code_011f
			pushi    #type
			pushi    1
			pushi    128
			lap      event
			send     6
			pushi    #said
			pushi    1
			lsp      event
			self     6
code_011f:
			ldi      0
			sag      lastEvent
			ret     
		)
	)
	
	(method (getInput param1 &tmp temp0 temp1)
		(if (!= (param1 type?) 4) (= inputLine 0))
		(if (!= (param1 message?) echo)
			(Format @inputLine 996 0 (param1 message?))
		)
		(= temp0 (Sound pause: blocks))
		(= temp1 (GetInput @inputLine inputLen prompt 67 x y))
		(Sound pause: temp0)
		(return temp1)
	)
	
	(method (canControl theControls)
		(if argc (= controls theControls) (= prevDir 0))
		(return controls)
	)
	
	(method (said param1)
		(if useSortedFeatures
			(SortedAdd alterEgo sortedFeatures cast features)
		else
			(sortedFeatures add: cast features)
		)
		(if TheMenuBar (sortedFeatures addToFront: TheMenuBar))
		(sortedFeatures
			addToEnd: theGame
			handleEvent: param1
			release:
		)
		(if
		(and (== (param1 type?) 128) (not (param1 claimed?)))
			(theGame pragmaFail: @inputLine)
		)
	)
)

(class Ego of Act
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $2000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		edgeHit 0
	)
	
	(method (init)
		(super init:)
		(if (not cycler) (self setCycle: Walk))
	)
	
	(method (doit)
		(super doit:)
		(= edgeHit
			(cond 
				((<= x 0) 4)
				((<= y (curRoom horizon?)) 1)
				((>= x 319) 2)
				((>= y 189) 3)
				(else 0)
			)
		)
	)
	
	(method (get param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			((inventory at: [param1 temp0]) moveTo: self)
			(++ temp0)
		)
	)
	
	(method (put param1 param2)
		(if (self has: param1)
			((inventory at: param1)
				moveTo: (if (== argc 1) -1 else param2)
			)
		)
	)
	
	(method (has param1 &tmp temp0)
		(= temp0 (inventory at: param1))
		(return (if (and temp0 (temp0 ownedBy: self)) 1 else 0))
	)
	
	(method (handleEvent event &tmp eventMessage)
		(if (not (super handleEvent: event))
			(switch (event type?)
				(evMOUSEBUTTON
					(if
						(and
							(not (& (event modifiers?) emSHIFT))
							(User controls?)
						)
						(self setMotion: MoveTo (event x?) (event y?))
						(User prevDir: 0)
						(event claimed: 1)
					)
				)
				(evJOYSTICK
					(= eventMessage (event message?))
					(if
						(and
							(== eventMessage (User prevDir?))
							(IsObject mover)
						)
						(= eventMessage 0)
					)
					(User prevDir: eventMessage)
					(self setDirection: eventMessage)
					(event claimed: 1)
				)
			)
		)
		(event claimed?)
	)
)

(instance uEvt of Event
	(properties)
)

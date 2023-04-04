;;; Sierra Script 1.0 - (do not remove this comment)
(script# 995)
(include sci.sh)
(use Main)
(use Intrface)
(use Save)
(use System)


(local
	yesI
	newDButton
	newDButton_2
	[local3 56]
)
(class InvI of Obj
	(properties
		said 0
		description 0
		owner 0
		view 0
		loop 0
		cel 0
		script 0
	)
	
	(method (saidMe)
		(Said said)
	)
	
	(method (ownedBy param1)
		(return (== owner param1))
	)
	
	(method (moveTo theOwner)
		(= owner theOwner)
		(return self)
	)
	
	(method (showSelf)
		(ShowView
			(if description description else name)
			view
			loop
			cel
		)
	)
	
	(method (changeState newState)
		(if script (script changeState: newState))
	)
)

(class Inv of Set
	(properties
		elements 0
		size 0
		carrying {You are carrying:}
		empty {You are carrying nothing!}
	)
	
	(method (init)
		(= inventory self)
	)
	
	(method (saidMe)
		(self firstTrue: #saidMe)
	)
	
	(method (ownedBy param1)
		(self firstTrue: #ownedBy param1)
	)
	
	(method (showSelf param1)
		(invD text: carrying doit: param1)
	)
)

(instance invD of Dialog
	(properties)
	
	(method (init param1 &tmp temp0 temp1 temp2 temp3 newDText inventoryFirst temp6)
		(= temp2 (= temp0 (= temp1 4)))
		(= temp3 0)
		(= inventoryFirst (inventory first:))
		(while inventoryFirst
			(= temp6 (NodeValue inventoryFirst))
			(if (temp6 ownedBy: param1)
				(++ temp3)
				(self
					add:
						((= newDText (DText new:))
							value: temp6
							text: (temp6 name?)
							nsLeft: temp0
							nsTop: temp1
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
				(if
				(< temp2 (- (newDText nsRight?) (newDText nsLeft?)))
					(= temp2 (- (newDText nsRight?) (newDText nsLeft?)))
				)
				(= temp1
					(+ temp1 (- (newDText nsBottom?) (newDText nsTop?)) 1)
				)
				(if (> temp1 140)
					(= temp1 4)
					(= temp0 (+ temp0 temp2 10))
					(= temp2 0)
				)
			)
			(= inventoryFirst (inventory next: inventoryFirst))
		)
		(if (not temp3) (self dispose:) (return 0))
		(= window SysWindow)
		(self setSize:)
		(= newDButton (DButton new:))
		(newDButton
			text: {Select}
			setSize:
			moveTo: (+ nsLeft 4 (newDButton nsLeft?)) nsBottom
		)
		(= newDButton_2 (DButton new:))
		(newDButton_2
			text: {Look_}
			setSize:
			moveTo: (+ nsLeft 4 (newDButton nsRight?)) nsBottom
		)
		(= yesI (DButton new:))
		(yesI
			text: {OK}
			setSize:
			moveTo: (+ nsLeft 4 (newDButton_2 nsRight?)) nsBottom
		)
		(self add: newDButton newDButton_2 yesI setSize: center:)
		(return temp3)
	)
	
	(method (doit param1 &tmp temp0)
		(asm
			pushi    #init
			pushi    1
			lsp      param1
			self     6
			not     
			bnt      code_020a
			pushi    1
			pushi    #empty
			pushi    0
			lag      inventory
			send     4
			push    
			calle    Print,  2
			ret     
code_020a:
			pushi    #open
			pushi    2
			pushi    4
			pushi    15
			self     8
			lal      yesI
			sat      temp0
			pushi    #setCursor
			pushi    1
			pushi    993
			lag      theGame
			send     6
code_0223:
			ldi      1
			bnt      code_06b9
			pushi    #doit
			pushi    1
			lst      temp0
			super    Dialog,  6
			sat      temp0
			lat      temp0
			not     
			bt       code_0245
			lst      temp0
			ldi      65535
			eq?     
			bt       code_0245
			lst      temp0
			lal      yesI
			eq?     
			bnt      code_0255
code_0245:
			lsg      theCursor
			ldi      993
			eq?     
			bnt      code_0252
			ldi      999
			sag      theCursor
code_0252:
			jmp      code_06b9
code_0255:
			lst      temp0
			lal      newDButton
			eq?     
			bt       code_026a
			lst      temp0
			lal      newDButton_2
			eq?     
			bt       code_026a
			lst      temp0
			lal      yesI
			eq?     
			bnt      code_02a5
code_026a:
			lst      temp0
			lal      newDButton
			eq?     
			bnt      code_0286
			lsg      theCursor
			ldi      993
			ne?     
			bnt      code_0284
			pushi    #setCursor
			pushi    1
			pushi    993
			lag      theGame
			send     6
code_0284:
			jmp      code_02a2
code_0286:
			lst      temp0
			lal      newDButton_2
			eq?     
			bnt      code_02a2
			lsg      theCursor
			ldi      998
			ne?     
			bnt      code_02a0
			pushi    #setCursor
			pushi    1
			pushi    998
			lag      theGame
			send     6
code_02a0:
			jmp      code_02a2
code_02a2:
			jmp      code_06b6
code_02a5:
			lsg      theCursor
			dup     
			ldi      993
			eq?     
			bnt      code_02d5
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			sag      gTheCursor
			pushi    #setCursor
			pushi    1
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			lag      theGame
			send     6
			jmp      code_06b5
code_02d5:
			dup     
			ldi      998
			eq?     
			bnt      code_0329
			pushi    10
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			eq?     
			bnt      code_031a
			pushi    #has
			pushi    1
			pushi    9
			lag      ego
			send     6
			not     
			bnt      code_031a
			pushi    #get
			pushi    1
			pushi    9
			lag      ego
			send     6
			pushi    #changeScore
			pushi    1
			pushi    100
			lag      theGame
			send     6
			pushi    2
			pushi    0
			pushi    42
			calle    Print,  4
			jmp      code_0326
code_031a:
			pushi    #showSelf
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
code_0326:
			jmp      code_06b5
code_0329:
			dup     
			ldi      19
			eq?     
			bnt      code_03f5
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      16
			eq?     
			bnt      code_03f2
			pushi    #has
			pushi    1
			pushi    16
			lag      ego
			send     6
			not     
			bnt      code_035f
			pushi    2
			pushi    540
			pushi    9
			calle    Print,  4
			jmp      code_03f2
code_035f:
			pushi    #has
			pushi    1
			pushi    19
			lag      ego
			send     6
			not     
			bnt      code_0395
			pushi    2
			pushi    540
			pushi    10
			calle    Print,  4
			lsg      global88
			ldi      3
			ge?     
			bnt      code_0393
			pushi    5
			pushi    540
			pushi    11
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
code_0393:
			jmp      code_03f2
code_0395:
			lsg      gCurRoomNum
			ldi      0
			ne?     
			bnt      code_03a2
			pushi    0
			callb    GoodIdea,  0
			jmp      code_03f2
code_03a2:
			pushi    1
			pushi    73
			callb    Btst,  2
			not     
			bnt      code_03cd
			pushi    2
			pushi    540
			pushi    12
			calle    Print,  4
			pushi    5
			pushi    540
			pushi    13
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
			jmp      code_03f2
code_03cd:
			pushi    1
			pushi    74
			callb    Btst,  2
			bnt      code_03e3
			pushi    2
			pushi    540
			pushi    14
			calle    Print,  4
			jmp      code_03f2
code_03e3:
			pushi    #changeState
			pushi    1
			pushi    5
			pushi    2
			pushi    540
			pushi    1
			callk    ScriptID,  4
			send     6
code_03f2:
			jmp      code_06b5
code_03f5:
			dup     
			ldi      16
			eq?     
			bnt      code_04c1
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      19
			eq?     
			bnt      code_04be
			pushi    #has
			pushi    1
			pushi    16
			lag      ego
			send     6
			not     
			bnt      code_042b
			pushi    2
			pushi    540
			pushi    9
			calle    Print,  4
			jmp      code_04be
code_042b:
			pushi    #has
			pushi    1
			pushi    19
			lag      ego
			send     6
			not     
			bnt      code_0461
			pushi    2
			pushi    540
			pushi    10
			calle    Print,  4
			lsg      global88
			ldi      3
			ge?     
			bnt      code_045f
			pushi    5
			pushi    540
			pushi    11
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
code_045f:
			jmp      code_04be
code_0461:
			lsg      gCurRoomNum
			ldi      0
			ne?     
			bnt      code_046e
			pushi    0
			callb    GoodIdea,  0
			jmp      code_04be
code_046e:
			pushi    1
			pushi    73
			callb    Btst,  2
			not     
			bnt      code_0499
			pushi    2
			pushi    540
			pushi    12
			calle    Print,  4
			pushi    5
			pushi    540
			pushi    13
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
			jmp      code_04be
code_0499:
			pushi    1
			pushi    74
			callb    Btst,  2
			bnt      code_04af
			pushi    2
			pushi    540
			pushi    14
			calle    Print,  4
			jmp      code_04be
code_04af:
			pushi    #changeState
			pushi    1
			pushi    5
			pushi    2
			pushi    540
			pushi    1
			callk    ScriptID,  4
			send     6
code_04be:
			jmp      code_06b5
code_04c1:
			dup     
			ldi      3
			eq?     
			bnt      code_05b7
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      21
			eq?     
			bt       code_0511
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      22
			eq?     
			bt       code_0511
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      34
			eq?     
			bt       code_0511
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      2
			eq?     
			bnt      code_05aa
code_0511:
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      22
			eq?     
			bnt      code_052f
			pushi    2
			pushi    0
			pushi    28
			calle    Print,  4
			jmp      code_05a8
code_052f:
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      34
			eq?     
			bnt      code_054d
			pushi    2
			pushi    0
			pushi    28
			calle    Print,  4
			jmp      code_05a8
code_054d:
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      2
			eq?     
			bnt      code_056b
			pushi    2
			pushi    0
			pushi    23
			calle    Print,  4
			jmp      code_05a8
code_056b:
			pushi    #has
			pushi    1
			pushi    2
			lag      ego
			send     6
			not     
			bnt      code_0583
			pushi    2
			pushi    0
			pushi    22
			calle    Print,  4
			jmp      code_05a8
code_0583:
			lsg      gCurRoomNum
			ldi      0
			eq?     
			bt       code_0591
			lsg      gCurRoomNum
			ldi      10
			eq?     
			bnt      code_05a4
code_0591:
			pushi    #setScript
			pushi    1
			pushi    1
			pushi    43
			callk    ScriptID,  2
			push    
			lag      theGame
			send     6
			jmp      code_06b9
			jmp      code_05a8
code_05a4:
			pushi    0
			callb    GoodIdea,  0
code_05a8:
			jmp      code_05b4
code_05aa:
			pushi    1
			lofss    {You can't use those items together.}
			calle    Print,  2
code_05b4:
			jmp      code_06b5
code_05b7:
			dup     
			ldi      2
			eq?     
			bnt      code_05e8
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      3
			eq?     
			bnt      code_05db
			pushi    2
			pushi    0
			pushi    23
			calle    Print,  4
			jmp      code_05e5
code_05db:
			pushi    1
			lofss    {You can't use those items together.}
			calle    Print,  2
code_05e5:
			jmp      code_06b5
code_05e8:
			dup     
			ldi      21
			eq?     
			bnt      code_06ab
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      3
			eq?     
			bt       code_0625
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      22
			eq?     
			bt       code_0625
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      34
			eq?     
			bnt      code_069f
code_0625:
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      22
			eq?     
			bnt      code_0643
			pushi    2
			pushi    0
			pushi    28
			calle    Print,  4
			jmp      code_069d
code_0643:
			pushi    #view
			pushi    0
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			send     4
			push    
			ldi      34
			eq?     
			bnt      code_0661
			pushi    2
			pushi    0
			pushi    28
			calle    Print,  4
			jmp      code_069d
code_0661:
			pushi    #has
			pushi    1
			pushi    2
			lag      ego
			send     6
			not     
			bnt      code_0679
			pushi    2
			pushi    0
			pushi    22
			calle    Print,  4
			jmp      code_069d
code_0679:
			lsg      gCurRoomNum
			ldi      0
			eq?     
			bt       code_0687
			lsg      gCurRoomNum
			ldi      10
			eq?     
			bnt      code_0699
code_0687:
			pushi    #setScript
			pushi    1
			pushi    1
			pushi    43
			callk    ScriptID,  2
			push    
			lag      theGame
			send     6
			jmp      code_06b9
			jmp      code_069d
code_0699:
			pushi    0
			callb    GoodIdea,  0
code_069d:
			jmp      code_06a9
code_069f:
			pushi    1
			lofss    {You can't use those items together.}
			calle    Print,  2
code_06a9:
			jmp      code_06b5
code_06ab:
			pushi    1
			lofss    {You can't use those items together.}
			calle    Print,  2
code_06b5:
			toss    
code_06b6:
			jmp      code_0223
code_06b9:
			pushi    #dispose
			pushi    0
			self     4
			ret     
		)
	)
	
	(method (handleEvent event &tmp eventMessage eventType)
		(= eventMessage (event message?))
		(= eventType (event type?))
		(switch eventType
			(evKEYBOARD
				(switch eventMessage
					(KEY_UP (= eventMessage 3840))
					(KEY_NUMPAD2 (= eventMessage 9))
				)
			)
			(evJOYSTICK
				(switch eventMessage
					(JOY_UP
						(= eventMessage 3840)
						(= eventType 4)
					)
					(JOY_DOWN
						(= eventMessage 9)
						(= eventType 4)
					)
				)
			)
		)
		(event type: eventType message: eventMessage)
		(super handleEvent: event)
	)
)

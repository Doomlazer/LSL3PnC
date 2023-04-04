;;; Sierra Script 1.0 - (do not remove this comment)
(script# 990)
(include sci.sh)
(use Main)
(use Intrface)
(use System)

(public
	GetDirectory 0
)

(local
	default
	i
	numGames
	selected
	status
	[okIText 5] = [{Restore} {__Save__} {Replace} {Replace}]
	[textIText 4] = [{Select the game that you would like to restore.} {Type the description of this saved game.} {This directory/disk can hold no more saved games. You must replace one of your saved games or use Change Directory to save on a different directory/disk.} {This directory/disk can hold no more saved games. You must replace one of your saved games or use Change Directory to save on a different directory/disk.}]
)
(procedure (GetDirectory param1 &tmp temp0 [temp1 33] [temp34 40])
	(return
		(repeat
			(= temp0
				(Print
					990
					1
					#font
					0
					#edit
					(StrCpy @temp1 param1)
					29
					#button
					{OK}
					1
					#button
					{Cancel}
					0
				)
			)
			(if (not temp0) (return 0))
			(if (not (StrLen @temp1)) (GetCWD @temp1))
			(if (ValidPath @temp1)
				(StrCpy param1 @temp1)
				(return 1)
			else
				(Print (Format @temp34 990 2 @temp1) #font 0)
			)
		)
	)
)

(procedure (localproc_000c)
	(return
		(cond 
			((== self Restore) 0)
			((localproc_00b4) 1)
			(numGames 2)
			(else 3)
		)
	)
)

(procedure (localproc_00b4)
	(return
		(if
		(and (< numGames 20) (CheckFreeSpace curSaveDir))
			1
		else
			0
		)
	)
)

(procedure (localproc_00c6)
	(Print 990 3 #font 0)
)

(class SysWindow of Obj
	(properties
		top 0
		left 0
		bottom 0
		right 0
		color 0
		back 15
		priority -1
		window 0
		type $0000
		title 0
		brTop 0
		brLeft 0
		brBottom 190
		brRight 320
	)
	
	(method (open)
		(= window
			(NewWindow
				top
				left
				bottom
				right
				title
				type
				priority
				color
				back
			)
		)
	)
	
	(method (dispose)
		(if window (DisposeWindow window) (= window 0))
		(super dispose:)
	)
)

(class SRDialog of Dialog
	(properties
		elements 0
		size 0
		text 0
		window 0
		theItem 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		time 0
		busy 0
		seconds 0
		lastSeconds 0
	)
	
	(method (init param1 param2 param3)
		(= window SysWindow)
		(= nsBottom 0)
		(= numGames
			(GetSaveFiles (theGame name?) param2 param3)
		)
		(if (== numGames -1) (return 0))
		(= status (localproc_000c))
		(if (== status 1)
			(editI
				text: (StrCpy param1 param2)
				font: smallFont
				setSize:
				moveTo: 4 4
			)
			(self add: editI setSize:)
		)
		(selectorI
			text: param2
			font: smallFont
			setSize:
			moveTo: 4 (+ nsBottom 4)
			state: 2
		)
		(= i (+ (selectorI nsRight?) 4))
		(okI
			text: [okIText status]
			setSize:
			moveTo: i (selectorI nsTop?)
			state: (if (== status 3) 0 else 3)
		)
		(cancelI
			setSize:
			moveTo: i (+ (okI nsBottom?) 4)
			state: (& (cancelI state?) $fff7)
		)
		(changeDirI
			setSize:
			moveTo: i (+ (cancelI nsBottom?) 4)
			state: (& (changeDirI state?) $fff7)
		)
		(self add: selectorI okI cancelI changeDirI setSize:)
		(textI
			text: [textIText status]
			setSize: (- (- nsRight nsLeft) 8)
			moveTo: 4 4
		)
		(= i (+ (textI nsBottom?) 4))
		(self eachElementDo: #move 0 i)
		(self add: textI setSize: center: open: 4 15)
		(return 1)
	)
	
	(method (doit param1 &tmp temp0 temp1 temp2 temp3 [temp4 361] [temp365 21] [temp386 40])
		(asm
			pushSelf
			class    Restore
			eq?     
			bnt      code_02ca
			lap      argc
			bnt      code_02ca
			lap      param1
			bnt      code_02ca
			pushi    1
			pushi    4
			lea      @temp386
			push    
			pushi    990
			pushi    0
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			callk    Format,  8
			push    
			callk    FOpen,  2
			sat      temp1
			lst      temp1
			ldi      65535
			eq?     
			bnt      code_02c4
			ret     
code_02c4:
			pushi    1
			lst      temp1
			callk    FClose,  2
code_02ca:
			pushi    #init
			pushi    3
			lsp      param1
			lea      @temp4
			push    
			lea      @temp365
			push    
			self     10
			not     
			bnt      code_02e2
			ldi      65535
			ret     
code_02e2:
			ldi      1
			bnt      code_04d6
			lsl      status
			dup     
			ldi      0
			eq?     
			bnt      code_02fd
			lal      numGames
			bnt      code_02f8
			lofsa    okI
			jmp      code_02fb
code_02f8:
			lofsa    changeDirI
code_02fb:
			jmp      code_0316
code_02fd:
			dup     
			ldi      1
			eq?     
			bnt      code_0308
			lofsa    editI
			jmp      code_0316
code_0308:
			dup     
			ldi      2
			eq?     
			bnt      code_0313
			lofsa    okI
			jmp      code_0316
code_0313:
			lofsa    changeDirI
code_0316:
			toss    
			sal      default
			pushi    #doit
			pushi    1
			lsl      default
			super    Dialog,  6
			sal      i
			pushi    #indexOf
			pushi    1
			pushi    #cursor
			pushi    0
			lofsa    selectorI
			send     4
			push    
			lofsa    selectorI
			send     6
			sal      selected
			lsl      selected
			ldi      18
			mul     
			sat      temp3
			lsl      i
			lofsa    changeDirI
			eq?     
			bnt      code_03da
			pushi    1
			lsg      curSaveDir
			call     GetDirectory,  2
			bnt      code_03d7
			pushi    3
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			lea      @temp4
			push    
			lea      @temp365
			push    
			callk    GetSaveFiles,  6
			sal      numGames
			lsl      numGames
			ldi      65535
			eq?     
			bnt      code_0377
			ldi      65535
			sat      temp2
			jmp      code_04d6
code_0377:
			lal      status
			sat      temp0
			pushi    0
			call     localproc_000c,  0
			sal      status
			lsl      status
			dup     
			ldi      0
			eq?     
			bnt      code_038c
			jmp      code_03ce
code_038c:
			dup     
			lat      temp0
			eq?     
			bnt      code_03b9
			pushi    #contains
			pushi    1
			lofss    editI
			self     6
			bnt      code_03b7
			pushi    #cursor
			pushi    1
			pushi    1
			pushi    2
			lsp      param1
			lea      @temp4
			push    
			callk    StrCpy,  4
			push    
			callk    StrLen,  2
			push    
			pushi    83
			pushi    0
			lofsa    editI
			send     10
code_03b7:
			jmp      code_03ce
code_03b9:
			pushi    #dispose
			pushi    0
			pushi    87
			pushi    3
			lsp      param1
			lea      @temp4
			push    
			lea      @temp365
			push    
			self     14
code_03ce:
			toss    
			pushi    #draw
			pushi    0
			lofsa    selectorI
			send     4
code_03d7:
			jmp      code_04d3
code_03da:
			lsl      status
			ldi      2
			eq?     
			bnt      code_040d
			lsl      i
			lofsa    okI
			eq?     
			bnt      code_040d
			pushi    #doit
			pushi    1
			pushi    2
			lsp      param1
			lat      temp3
			leai     @temp4
			push    
			callk    StrCpy,  4
			push    
			lofsa    GetReplaceName
			send     6
			bnt      code_040a
			lal      selected
			lati     temp365
			sat      temp2
			jmp      code_04d6
code_040a:
			jmp      code_04d3
code_040d:
			lsl      status
			ldi      1
			eq?     
			bnt      code_0485
			lsl      i
			lofsa    okI
			eq?     
			bt       code_0424
			lsl      i
			lofsa    editI
			eq?     
			bnt      code_0485
code_0424:
			pushi    1
			lsp      param1
			callk    StrLen,  2
			push    
			ldi      0
			eq?     
			bnt      code_0438
			pushi    0
			call     localproc_00c6,  0
			jmp      code_02e2
code_0438:
			ldi      65535
			sat      temp2
			ldi      0
			sal      i
code_0440:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_0463
			pushi    2
			lsp      param1
			lsl      i
			ldi      18
			mul     
			leai     @temp4
			push    
			callk    StrCmp,  4
			sat      temp2
			lat      temp2
			not     
			bnt      code_045f
			jmp      code_0463
code_045f:
			+al      i
			jmp      code_0440
code_0463:
			lat      temp2
			not     
			bnt      code_046f
			lal      i
			lati     temp365
			jmp      code_047f
code_046f:
			lsl      numGames
			ldi      20
			eq?     
			bnt      code_047d
			lal      selected
			lati     temp365
			jmp      code_047f
code_047d:
			lal      numGames
code_047f:
			sat      temp2
			jmp      code_04d6
			jmp      code_04d3
code_0485:
			lsl      i
			lofsa    okI
			eq?     
			bnt      code_0498
			lal      selected
			lati     temp365
			sat      temp2
			jmp      code_04d6
			jmp      code_04d3
code_0498:
			lsl      i
			ldi      0
			eq?     
			bt       code_04a7
			lsl      i
			lofsa    cancelI
			eq?     
			bnt      code_04af
code_04a7:
			ldi      65535
			sat      temp2
			jmp      code_04d6
			jmp      code_04d3
code_04af:
			lsl      status
			ldi      1
			eq?     
			bnt      code_04d3
			pushi    #cursor
			pushi    1
			pushi    1
			pushi    2
			lsp      param1
			lat      temp3
			leai     @temp4
			push    
			callk    StrCpy,  4
			push    
			callk    StrLen,  2
			push    
			pushi    83
			pushi    0
			lofsa    editI
			send     10
code_04d3:
			jmp      code_02e2
code_04d6:
			pushi    #dispose
			pushi    0
			self     4
			lat      temp2
			ret     
		)
	)
)

(class Restore of SRDialog
	(properties
		elements 0
		size 0
		text {Restore a Game}
		window 0
		theItem 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		time 0
		busy 0
		seconds 0
		lastSeconds 0
	)
)

(class Save of SRDialog
	(properties
		elements 0
		size 0
		text {Save a Game}
		window 0
		theItem 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		time 0
		busy 0
		seconds 0
		lastSeconds 0
	)
)

(instance GetReplaceName of Dialog
	(properties)
	
	(method (doit param1 &tmp temp0)
		(= window SysWindow)
		(text1 setSize: moveTo: 4 4)
		(self add: text1 setSize:)
		(oldName
			text: param1
			font: smallFont
			setSize:
			moveTo: 4 nsBottom
		)
		(self add: oldName setSize:)
		(text2 setSize: moveTo: 4 nsBottom)
		(self add: text2 setSize:)
		(newName
			text: param1
			font: smallFont
			setSize:
			moveTo: 4 nsBottom
		)
		(self add: newName setSize:)
		(button1 nsLeft: 0 nsTop: 0 setSize:)
		(button2 nsLeft: 0 nsTop: 0 setSize:)
		(button2
			moveTo: (- nsRight (+ (button2 nsRight?) 4)) nsBottom
		)
		(button1
			moveTo: (- (button2 nsLeft?) (+ (button1 nsRight?) 4)) nsBottom
		)
		(self add: button1 button2 setSize: center: open: 0 15)
		(= temp0 (super doit: newName))
		(self dispose:)
		(if (not (StrLen param1))
			(localproc_00c6)
			(= temp0 0)
		)
		(return
			(if (or (== temp0 newName) (== temp0 button1))
				1
			else
				0
			)
		)
	)
)

(instance selectorI of DSelector
	(properties
		x 36
		y 8
	)
)

(instance editI of DEdit
	(properties
		max 35
	)
)

(instance okI of DButton
	(properties)
)

(instance cancelI of DButton
	(properties
		text { Cancel_}
	)
)

(instance changeDirI of DButton
	(properties
		text {Change\nDirectory}
	)
)

(instance textI of DText
	(properties
		font 0
	)
)

(instance text1 of DText
	(properties
		text {Replace}
		font 0
	)
)

(instance text2 of DText
	(properties
		text {with:}
		font 0
	)
)

(instance oldName of DText
	(properties)
)

(instance newName of DEdit
	(properties
		max 35
	)
)

(instance button1 of DButton
	(properties
		text {Replace}
	)
)

(instance button2 of DButton
	(properties
		text {Cancel}
	)
)

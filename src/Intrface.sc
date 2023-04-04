;;; Sierra Script 1.0 - (do not remove this comment)
(script# 255)
(include sci.sh)
(use Main)
(use System)

(public
	Print 0
	ShowView 1
	GetInput 2
	GetNumber 3
	Printf 4
	MousedOn 5
	proc255_6 6
)

(procedure (Print param1 param2 &tmp newDialog newDText newDIcon newDEdit temp4 temp5 temp6 temp7 temp8 theModelessDialog temp10 temp11 [newDButton 6] temp18 temp19 temp20 [temp21 1000])
	(= temp6 (= temp7 -1))
	(= theModelessDialog
		(= temp8
			(= temp18 (= newDIcon (= newDEdit (= temp19 0))))
		)
	)
	((= newDialog (Dialog new:))
		window: systemWindow
		name: {PrintD}
	)
	(= newDText (DText new:))
	(cond 
		((u< param1 1000) (GetFarText param1 param2 @temp21) (= temp5 2))
		(param1 (StrCpy @temp21 param1) (= temp5 1))
		(else (= temp21 0) (= temp5 0))
	)
	(newDText
		text: @temp21
		moveTo: 4 4
		font: userFont
		setSize:
	)
	(newDialog add: newDText)
	(= temp5 temp5)
	(while (< temp5 argc)
		(switch [param1 temp5]
			(30
				(++ temp5)
				(newDText mode: [param1 temp5])
			)
			(33
				(++ temp5)
				(newDText font: [param1 temp5] setSize: temp8)
			)
			(70
				(++ temp5)
				(= temp8 [param1 temp5])
				(newDText setSize: temp8)
			)
			(25
				(++ temp5)
				(newDialog time: [param1 temp5])
			)
			(80
				(++ temp5)
				(newDialog text: [param1 temp5])
			)
			(67
				(++ temp5)
				(= temp6 [param1 temp5])
				(++ temp5)
				(= temp7 [param1 temp5])
			)
			(83
				(Animate (cast elements?) 0)
			)
			(41
				(++ temp5)
				((= newDEdit (DEdit new:)) text: [param1 temp5])
				(++ temp5)
				(newDEdit max: [param1 temp5] setSize:)
			)
			(81
				((= [newDButton temp19] (DButton new:))
					text: [param1 (++ temp5)]
					value: [param1 (++ temp5)]
					setSize:
				)
				(= temp18 (+ temp18 ([newDButton temp19] nsRight?) 4))
				(++ temp19)
			)
			(82
				(if (IsObject [param1 (+ temp5 1)])
					(= newDIcon ([param1 (+ temp5 1)] new:))
					(newDIcon setSize:)
					(= temp5 (+ temp5 1))
				else
					(= newDIcon (DIcon new:))
					(newDIcon
						view: [param1 (+ temp5 1)]
						loop: [param1 (+ temp5 2)]
						cel: [param1 (+ temp5 3)]
						setSize:
					)
					(= temp5 (+ temp5 3))
				)
			)
			(88
				(if modelessDialog (modelessDialog dispose:))
				(= theModelessDialog newDialog)
			)
			(35
				(++ temp5)
				(newDialog window: [param1 temp5])
			)
		)
		(++ temp5)
	)
	(if newDIcon
		(newDIcon moveTo: 4 4)
		(newDText
			moveTo: (+ 4 (newDIcon nsRight?)) (newDText nsTop?)
		)
		(newDialog add: newDIcon)
	)
	(newDialog setSize:)
	(if newDEdit
		(newDEdit
			moveTo: (newDText nsLeft?) (+ 4 (newDText nsBottom?))
		)
		(newDialog add: newDEdit setSize:)
	)
	(= temp20
		(if (> temp18 (newDialog nsRight?))
			4
		else
			(- (newDialog nsRight?) temp18)
		)
	)
	(= temp5 0)
	(while (< temp5 temp19)
		([newDButton temp5] moveTo: temp20 (newDialog nsBottom?))
		(newDialog add: [newDButton temp5])
		(= temp20 (+ 4 ([newDButton temp5] nsRight?)))
		(++ temp5)
	)
	(newDialog setSize: center:)
	(if (and newDIcon (not (StrLen @temp21)))
		(newDIcon
			moveTo:
				(/
					(-
						(- (newDialog nsRight?) (newDialog nsLeft?))
						(- (newDIcon nsRight?) (newDIcon nsLeft?))
					)
					2
				)
				4
		)
	)
	(newDialog
		moveTo:
			(if (== -1 temp6) (newDialog nsLeft?) else temp6)
			(if (== -1 temp7) (newDialog nsTop?) else temp7)
	)
	(= temp11 (GetPort))
	(newDialog
		open: (if (newDialog text?) 4 else 0) (if theModelessDialog 15 else -1)
	)
	(if theModelessDialog
		(= modelessPort (GetPort))
		(SetPort temp11)
		(return (= modelessDialog theModelessDialog))
	)
	(if
		(and
			(= temp10 (newDialog firstTrue: #checkState 1))
			(not (newDialog firstTrue: #checkState 2))
		)
		(temp10 state: (| (temp10 state?) $0002))
	)
	(= temp4 (newDialog doit: temp10))
	(if (== temp4 -1) (= temp4 0))
	(= temp5 0)
	(while (< temp5 temp19)
		(if (== temp4 [newDButton temp5])
			(= temp4 (temp4 value?))
			(break)
		)
		(++ temp5)
	)
	(if (not (newDialog theItem?)) (= temp4 1))
	(newDialog dispose:)
	(return temp4)
)

(procedure (ShowView param1 param2 param3 param4)
	(Print param1 #icon param2 param3 param4 &rest)
)

(procedure (GetInput param1 param2 param3 &tmp [temp0 4])
	(return
		(if
			(and
				(Print
					(if (>= argc 3) param3 else {})
					#edit
					param1
					param2
					&rest
				)
				(StrLen param1)
			)
			1
		else
			0
		)
	)
)

(procedure (GetNumber param1 param2 &tmp [temp0 40])
	(= temp0 0)
	(if (> argc 1) (Format @temp0 {%d} param2))
	(return
		(if (GetInput @temp0 5 param1)
			(ReadNumber @temp0)
		else
			-1
		)
	)
)

(procedure (Printf &tmp [temp0 500])
	(Format @temp0 &rest)
	(Print @temp0)
)

(procedure (MousedOn param1 param2 param3)
	(cond 
		(
			(or
				(!= (param2 type?) 1)
				(and
					(param1 respondsTo: #signal)
					(& (param1 signal?) $0080)
				)
			)
			0
		)
		(
			(and
				(>= argc 3)
				param3
				(== (& (param2 modifiers?) param3) 0)
			)
			0
		)
		((param1 respondsTo: #nsLeft)
			(InRect
				(param1 nsLeft?)
				(param1 nsTop?)
				(param1 nsRight?)
				(param1 nsBottom?)
				param2
			)
		)
	)
)

(procedure ( param1 param2 &tmp newDialog newDText newDIcon newDEdit temp4 temp5 temp6 temp7 temp8 theModelessDialog temp10 temp11 [newDButton 6] temp18 temp19 temp20 [temp21 1000] temp1021)
	(= temp6 (= temp7 -1))
	(= theModelessDialog
		(= temp8
			(= temp18 (= newDIcon (= newDEdit (= temp19 0))))
		)
	)
	(= temp1021 0)
	((= newDialog (Dialog new:))
		window: systemWindow
		name: {PrintD}
	)
	(= newDText (DText new:))
	(cond 
		((u< param1 1000) (GetFarText param1 param2 @temp21) (= temp5 2))
		(param1 (StrCpy @temp21 param1) (= temp5 1))
		(else (= temp21 0) (= temp5 0))
	)
	(newDText
		text: @temp21
		moveTo: 4 4
		font: userFont
		setSize:
	)
	(newDialog add: newDText)
	(= temp5 temp5)
	(while (< temp5 argc)
		(switch [param1 temp5]
			(30
				(++ temp5)
				(newDText mode: [param1 temp5])
			)
			(33
				(++ temp5)
				(newDText font: [param1 temp5] setSize: temp8)
			)
			(70
				(++ temp5)
				(= temp8 [param1 temp5])
				(newDText setSize: temp8)
			)
			(25
				(++ temp5)
				(newDialog time: [param1 temp5])
			)
			(80
				(++ temp5)
				(newDialog text: [param1 temp5])
			)
			(67
				(++ temp5)
				(= temp6 [param1 temp5])
				(++ temp5)
				(= temp7 [param1 temp5])
			)
			(83
				(Animate (cast elements?) 0)
			)
			(41
				(++ temp5)
				((= newDEdit (DEdit new:)) text: [param1 temp5])
				(++ temp5)
				(newDEdit max: [param1 temp5] setSize:)
			)
			(81
				((= [newDButton temp19] (DButton new:))
					text: [param1 (++ temp5)]
					value: [param1 (++ temp5)]
					setSize:
				)
				(= temp18 (+ temp18 ([newDButton temp19] nsRight?) 4))
				(++ temp19)
			)
			(82
				(if (IsObject [param1 (+ temp5 1)])
					(= newDIcon ([param1 (+ temp5 1)] new:))
					(newDIcon setSize:)
					(= temp5 (+ temp5 1))
				else
					(= newDIcon (DIcon new:))
					(newDIcon
						view: [param1 (+ temp5 1)]
						loop: [param1 (+ temp5 2)]
						cel: [param1 (+ temp5 3)]
						setSize:
					)
					(= temp5 (+ temp5 3))
				)
			)
			(88
				(if modelessDialog (modelessDialog dispose:))
				(= theModelessDialog newDialog)
			)
			(35
				(++ temp5)
				(newDialog window: [param1 temp5])
			)
		)
		(++ temp5)
	)
	(if newDIcon
		(newDIcon moveTo: 4 4)
		(newDText
			moveTo: (+ 4 (newDIcon nsRight?)) (newDText nsTop?)
		)
		(newDialog add: newDIcon)
	)
	(newDialog setSize:)
	(if newDEdit
		(newDEdit
			moveTo: (newDText nsLeft?) (+ 4 (newDText nsBottom?))
		)
		(newDialog add: newDEdit setSize:)
	)
	(= temp20
		(if (> temp18 (newDialog nsRight?))
			4
		else
			(- (newDialog nsRight?) temp18)
		)
	)
	(= temp5 0)
	(while (< temp5 temp19)
		(if (> temp20 150)
			(= temp20 4)
			(= temp1021
				(+ temp1021 ([newDButton temp5] nsBottom?) 4)
			)
		)
		([newDButton temp5]
			moveTo: temp20 (+ (newDialog nsBottom?) temp1021)
		)
		(newDialog add: [newDButton temp5])
		(= temp20 (+ 4 ([newDButton temp5] nsRight?)))
		(++ temp5)
	)
	(newDialog setSize: center:)
	(if (and newDIcon (not (StrLen @temp21)))
		(newDIcon
			moveTo:
				(/
					(-
						(- (newDialog nsRight?) (newDialog nsLeft?))
						(- (newDIcon nsRight?) (newDIcon nsLeft?))
					)
					2
				)
				4
		)
	)
	(newDialog
		moveTo:
			(if (== -1 temp6) (newDialog nsLeft?) else temp6)
			(if (== -1 temp7) (newDialog nsTop?) else temp7)
	)
	(= temp11 (GetPort))
	(newDialog
		open: (if (newDialog text?) 4 else 0) (if theModelessDialog 15 else -1)
	)
	(if theModelessDialog
		(= modelessPort (GetPort))
		(SetPort temp11)
		(return (= modelessDialog theModelessDialog))
	)
	(if
		(and
			(= temp10 (newDialog firstTrue: #checkState 1))
			(not (newDialog firstTrue: #checkState 2))
		)
		(temp10 state: (| (temp10 state?) $0002))
	)
	(= temp4 (newDialog doit: temp10))
	(if (== temp4 -1) (= temp4 0))
	(= temp5 0)
	(while (< temp5 temp19)
		(if (== temp4 [newDButton temp5])
			(= temp4 (temp4 value?))
			(break)
		)
		(++ temp5)
	)
	(if (not (newDialog theItem?)) (= temp4 1))
	(newDialog dispose:)
	(return temp4)
)

(procedure (localproc_0018 &tmp newEvent temp1)
	(= newEvent (Event new:))
	(= temp1 (!= (newEvent type?) 2))
	(newEvent dispose:)
	(return temp1)
)

(class Class_255_0 of Obj
	(properties
		state $0000
	)
	
	(method (draw)
		(= state 1)
		(DrawMenuBar 1)
	)
	
	(method (hide)
		(DrawMenuBar 0)
	)
	
	(method (add)
		(AddMenu &rest)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(= temp0 0)
		(if state
			(= temp1 (Joystick 12 30))
			(= temp0 (MenuSelect event &rest))
			(Joystick 12 temp1)
		)
		(return temp0)
	)
)

(class Class_255_1 of Obj
	(properties
		type $0000
		state $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
	)
	
	(method (enable param1)
		(if param1
			(= state (| state $0001))
		else
			(= state (& state $fffe))
		)
	)
	
	(method (select param1)
		(if param1
			(= state (| state $0008))
		else
			(= state (& state $fff7))
		)
		(self draw:)
	)
	
	(method (handleEvent event &tmp temp0 eventType temp2)
		(if (event claimed?) (return 0))
		(= temp0 0)
		(if (& state $0001)
			(= eventType (event type?))
			(if
				(or
					(and (== eventType evSAID) (Said said))
					(and
						(== eventType evKEYBOARD)
						(== (event message?) key)
					)
					(and (== eventType evMOUSEBUTTON) (self check: event))
				)
				(event claimed: 1)
				(= temp0 (self track: event))
			)
		)
		(return temp0)
	)
	
	(method (check param1)
		(return
			(if
				(and
					(>= (param1 x?) nsLeft)
					(>= (param1 y?) nsTop)
					(< (param1 x?) nsRight)
					(< (param1 y?) nsBottom)
				)
				1
			else
				0
			)
		)
	)
	
	(method (track param1 &tmp temp0 temp1)
		(return
			(if (== 1 (param1 type?))
				(= temp1 0)
				(repeat
					(= param1 (Event new: -32768))
					(GlobalToLocal param1)
					(= temp0 (self check: param1))
					(if (!= temp0 temp1)
						(HiliteControl self)
						(= temp1 temp0)
					)
					(param1 dispose:)
					(if (not (localproc_0018)) (break))
				)
				(if temp0 (HiliteControl self))
				(return temp0)
			else
				(return self)
			)
		)
	)
	
	(method (isType param1)
		(return (== type param1))
	)
	
	(method (checkState param1)
		(return (& state param1))
	)
	
	(method (doit)
		(return value)
	)
	
	(method (setSize)
	)
	
	(method (move param1 param2)
		(= nsRight (+ nsRight param1))
		(= nsLeft (+ nsLeft param1))
		(= nsTop (+ nsTop param2))
		(= nsBottom (+ nsBottom param2))
	)
	
	(method (moveTo param1 param2)
		(self move: (- param1 nsLeft) (- param2 nsTop))
	)
	
	(method (draw)
		(DrawControl self)
	)
	
	(method (cycle)
	)
)

(class DText of Class_255_1
	(properties
		type $0002
		state $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		text 0
		font 1
		mode 0
	)
	
	(method (new &tmp temp0)
		((super new:) font: userFont yourself:)
	)
	
	(method (setSize param1 &tmp [temp0 2] temp2 temp3)
		(TextSize @temp0 text font (if argc param1 else 0))
		(= nsBottom (+ nsTop temp2))
		(= nsRight (+ nsLeft temp3))
	)
)

(class DIcon of Class_255_1
	(properties
		type $0004
		state $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		view 0
		loop 0
		cel 0
	)
	
	(method (setSize &tmp [temp0 4])
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
	)
)

(class DButton of Class_255_1
	(properties
		type $0001
		state $0003
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		text 0
		font 0
	)
	
	(method (setSize &tmp [temp0 2] temp2 temp3)
		(TextSize @temp0 text font)
		(= temp2 (+ temp2 2))
		(= temp3 (+ temp3 2))
		(= nsBottom (+ nsTop temp2))
		(= temp3 (* (/ (+ temp3 15) 16) 16))
		(= nsRight (+ temp3 nsLeft))
	)
)

(class DEdit of Class_255_1
	(properties
		type $0003
		state $0001
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		text 0
		font 0
		max 0
		cursor 0
	)
	
	(method (track param1)
		(EditControl self param1)
		(return self)
	)
	
	(method (setSize &tmp [temp0 2] temp2 temp3)
		(TextSize @temp0 {M} font)
		(= nsBottom (+ nsTop temp2))
		(= nsRight (+ nsLeft (/ (* temp3 max 3) 4)))
		(= cursor (StrLen text))
	)
)

(class DSelector of Class_255_1
	(properties
		type $0006
		state $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		font 0
		x 20
		y 6
		text 0
		cursor 0
		lsTop 0
		mark 0
	)
	
	(method (indexOf param1 &tmp theText temp1)
		(= theText text)
		(= temp1 0)
		(return
			(while (< temp1 300)
				(if (== 0 (StrLen theText)) (return -1))
				(if (not (StrCmp param1 theText)) (return temp1))
				(= theText (+ theText x))
				(++ temp1)
			)
		)
	)
	
	(method (at param1)
		(return (+ text (* x param1)))
	)
	
	(method (setSize &tmp [temp0 2] temp2 temp3)
		(TextSize @temp0 {M} font)
		(= nsBottom (+ nsTop 20 (* temp2 y)))
		(= nsRight (+ nsLeft (/ (* temp3 x 3) 4)))
		(= lsTop (= cursor text))
		(= mark 0)
	)
	
	(method (retreat param1 &tmp temp0)
		(asm
			ldi      0
			sat      temp0
code_0d19:
			lap      param1
			bnt      code_0d46
			pTos     cursor
			pToa     text
			ne?     
			bnt      code_0d42
			ldi      1
			sat      temp0
			pTos     cursor
			pToa     x
			sub     
			aTop     cursor
			pToa     mark
			bnt      code_0d37
			dpToa    mark
			jmp      code_0d3e
code_0d37:
			pTos     lsTop
			pToa     x
			sub     
			aTop     lsTop
code_0d3e:
			-ap      param1
			jmp      code_0d44
code_0d42:
			jmp      code_0d46
code_0d44:
			jmp      code_0d19
code_0d46:
			lat      temp0
			bnt      code_0d4f
			pushi    #draw
			pushi    0
			self     4
code_0d4f:
			ret     
		)
	)
	
	(method (advance param1 &tmp temp0)
		(asm
			ldi      0
			sat      temp0
code_0d56:
			lap      param1
			bnt      code_0d8d
			pushi    2
			pTos     cursor
			pTos     x
			callk    StrAt,  4
			bnt      code_0d89
			ldi      1
			sat      temp0
			pTos     cursor
			pToa     x
			add     
			aTop     cursor
			pTos     mark
			ldi      1
			add     
			push    
			pToa     y
			lt?     
			bnt      code_0d7e
			ipToa    mark
			jmp      code_0d85
code_0d7e:
			pTos     lsTop
			pToa     x
			add     
			aTop     lsTop
code_0d85:
			-ap      param1
			jmp      code_0d8b
code_0d89:
			jmp      code_0d8d
code_0d8b:
			jmp      code_0d56
code_0d8d:
			lat      temp0
			bnt      code_0d96
			pushi    #draw
			pushi    0
			self     4
code_0d96:
			ret     
		)
	)
	
	(method (handleEvent event &tmp temp0 [temp1 3] temp4 [temp5 2] temp7 temp8)
		(if (event claimed?) (return 0))
		(if (== evJOYSTICK (event type?))
			(event type: 4)
			(switch (event message?)
				(JOY_DOWN
					(event message: 20480)
				)
				(JOY_UP (event message: 18432))
				(else  (event type: 64))
			)
		)
		(= temp0 0)
		(switch (event type?)
			(evKEYBOARD
				(event claimed: 1)
				(switch (event message?)
					(KEY_NUMPAD7 (self retreat: 50))
					(KEY_NUMPAD1 (self advance: 50))
					(KEY_PAGEUP
						(self advance: (- y 1))
					)
					(KEY_PAGEDOWN
						(self retreat: (- y 1))
					)
					(KEY_NUMPAD2 (self advance: 1))
					(KEY_UP (self retreat: 1))
					(else  (event claimed: 0))
				)
			)
			(evMOUSEBUTTON
				(if (self check: event)
					(event claimed: 1)
					(cond 
						((< (event y?) (+ nsTop 10))
							(repeat
								(self retreat: 1)
								(if (not (localproc_0018)) (break))
							)
						)
						((> (event y?) (- nsBottom 10))
							(repeat
								(self advance: 1)
								(if (not (localproc_0018)) (break))
							)
						)
						(else
							(TextSize @temp5 {M} font)
							(= temp4 (/ (- (event y?) (+ nsTop 10)) temp7))
							(if (> temp4 mark)
								(self advance: (- temp4 mark))
							else
								(self retreat: (- mark temp4))
							)
						)
					)
				)
			)
		)
		(return (if (and (event claimed?) (& state $0002)) self else 0))
	)
)

(class Dialog of List
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
	
	(method (open param1 param2)
		(if (and (PicNotValid) cast)
			(Animate (cast elements?) 0)
		)
		(= window (window new:))
		(window
			top: nsTop
			left: nsLeft
			bottom: nsBottom
			right: nsRight
			title: text
			type: param1
			priority: param2
			open:
		)
		(= seconds time)
		(self draw:)
	)
	
	(method (draw)
		(self eachElementDo: #draw)
	)
	
	(method (doit param1 &tmp temp0 newEvent temp2 temp3 temp4)
		(= temp0 0)
		(= busy 1)
		(self eachElementDo: #init)
		(if theItem (theItem select: 0))
		(= theItem
			(if (and argc param1)
				param1
			else
				(self firstTrue: #checkState 1)
			)
		)
		(if theItem (theItem select: 1))
		(if (not theItem)
			(= temp3 60)
			(= temp4 (GetTime))
		else
			(= temp3 0)
		)
		(= temp2 0)
		(while (not temp2)
			(self eachElementDo: #cycle)
			(GlobalToLocal (= newEvent (Event new:)))
			(if temp3
				(-- temp3)
				(if (== (newEvent type?) 1) (newEvent type: 1))
				(while (== temp4 (GetTime))
				)
				(= temp4 (GetTime))
			)
			(= temp2 (self handleEvent: newEvent))
			(newEvent dispose:)
			(self check:)
			(if (or (== temp2 -1) (not busy))
				(= temp2 0)
				(EditControl theItem 0)
				(break)
			)
			(Wait 1)
		)
		(= busy 0)
		(return temp2)
	)
	
	(method (check &tmp theLastSeconds)
		(if seconds
			(= theLastSeconds (GetTime 1))
			(if (!= lastSeconds theLastSeconds)
				(= lastSeconds theLastSeconds)
				(if (not (-- seconds)) (self cue:))
			)
		)
	)
	
	(method (cue)
		(if (not busy) (self dispose:) else (= busy 0))
	)
	
	(method (dispose)
		(if (== self modelessDialog)
			(SetPort modelessPort)
			(= modelessDialog 0)
			(= modelessPort 0)
		)
		(if window (window dispose:))
		(= window 0)
		(= theItem 0)
		(super dispose:)
	)
	
	(method (advance &tmp temp0 dialogFirst)
		(if theItem
			(theItem select: 0)
			(= dialogFirst (self contains: theItem))
			(repeat
				(if (not (= dialogFirst (self next: dialogFirst)))
					(= dialogFirst (self first:))
				)
				(= theItem (NodeValue dialogFirst))
				(if (& (theItem state?) $0001) (break))
			)
			(theItem select: 1)
		)
	)
	
	(method (retreat &tmp temp0 dialogLast)
		(if theItem
			(theItem select: 0)
			(= dialogLast (self contains: theItem))
			(repeat
				(if (not (= dialogLast (self prev: dialogLast)))
					(= dialogLast (self last:))
				)
				(= theItem (NodeValue dialogLast))
				(if (& (theItem state?) $0001) (break))
			)
			(theItem select: 1)
		)
	)
	
	(method (handleEvent event &tmp theTheItem)
		(if
			(or
				(event claimed?)
				(== (event type?) evNULL)
				(and
					(!= evMOUSEBUTTON (event type?))
					(!= evKEYBOARD (event type?))
					(!= evJOYSTICK (event type?))
					(!= evJOYDOWN (event type?))
				)
			)
			(EditControl theItem event)
			(return 0)
		)
		(if
		(= theTheItem (self firstTrue: #handleEvent event))
			(EditControl theItem 0)
			(if (not (theTheItem checkState: 2))
				(if theItem (theItem select: 0))
				((= theItem theTheItem) select: 1)
				(theTheItem doit:)
				(= theTheItem 0)
			)
		else
			(= theTheItem 0)
			(cond 
				(
					(and
						(or
							(== (event type?) evJOYDOWN)
							(and
								(== evKEYBOARD (event type?))
								(== KEY_RETURN (event message?))
							)
						)
						theItem
						(theItem checkState: 1)
					)
					(= theTheItem theItem)
					(EditControl theItem 0)
					(event claimed: 1)
				)
				(
					(or
						(and
							(not (self firstTrue: #checkState 1))
							(or
								(and
									(== evKEYBOARD (event type?))
									(== KEY_RETURN (event message?))
								)
								(== evMOUSEBUTTON (event type?))
								(== evJOYDOWN (event type?))
							)
						)
						(and
							(== evKEYBOARD (event type?))
							(== KEY_ESCAPE (event message?))
						)
					)
					(event claimed: 1)
					(= theTheItem -1)
				)
				(
					(and
						(== evKEYBOARD (event type?))
						(== KEY_TAB (event message?))
					)
					(event claimed: 1)
					(self advance:)
				)
				(
					(and
						(== evKEYBOARD (event type?))
						(== KEY_SHIFTTAB (event message?))
					)
					(event claimed: 1)
					(self retreat:)
				)
				(else (EditControl theItem event))
			)
		)
		(return theTheItem)
	)
	
	(method (move param1 param2)
		(= nsRight (+ nsRight param1))
		(= nsLeft (+ nsLeft param1))
		(= nsTop (+ nsTop param2))
		(= nsBottom (+ nsBottom param2))
	)
	
	(method (moveTo param1 param2)
		(self move: (- param1 nsLeft) (- param2 nsTop))
	)
	
	(method (center)
		(self
			moveTo:
				(+
					(window brLeft?)
					(/
						(-
							(- (window brRight?) (window brLeft?))
							(- nsRight nsLeft)
						)
						2
					)
				)
				(+
					(window brTop?)
					(/
						(-
							(- (window brBottom?) (window brTop?))
							(- nsBottom nsTop)
						)
						2
					)
				)
		)
	)
	
	(method (setSize &tmp dialogFirst temp1 theNsTop theNsLeft theNsBottom theNsRight)
		(if text
			(TextSize @theNsTop text 0 -1)
			(= nsTop theNsTop)
			(= nsLeft theNsLeft)
			(= nsBottom theNsBottom)
			(= nsRight theNsRight)
		else
			(= nsTop 0)
			(= nsLeft 0)
			(= nsBottom 0)
			(= nsRight 0)
		)
		(= dialogFirst (self first:))
		(while dialogFirst
			(= temp1 (NodeValue dialogFirst))
			(if (< (temp1 nsLeft?) nsLeft)
				(= nsLeft (temp1 nsLeft?))
			)
			(if (< (temp1 nsTop?) nsTop) (= nsTop (temp1 nsTop?)))
			(if (> (temp1 nsRight?) nsRight)
				(= nsRight (temp1 nsRight?))
			)
			(if (> (temp1 nsBottom?) nsBottom)
				(= nsBottom (temp1 nsBottom?))
			)
			(= dialogFirst (self next: dialogFirst))
		)
		(= nsRight (+ nsRight 4))
		(= nsBottom (+ nsBottom 4))
		(self moveTo: 0 0)
	)
)

(class Controls of List
	(properties
		elements 0
		size 0
	)
	
	(method (draw)
		(self eachElementDo: #setSize)
		(self eachElementDo: #draw)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return 0))
		(if
			(and
				(= temp0 (self firstTrue: #handleEvent event))
				(not (temp0 checkState: 2))
			)
			(temp0 doit:)
			(= temp0 0)
		)
		(return temp0)
	)
)

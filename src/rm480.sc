;;; Sierra Script 1.0 - (do not remove this comment)
(script# 480)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm480 0
)

(local
	local0
	[str 200]
)
(procedure (localproc_000c &tmp temp0)
	(Print
		@str
		#at
		10
		10
		#title
		{Patti says...}
		#width
		140
		#mode
		1
		#time
		(= temp0 (PrintDelay @str))
		#dispose
	)
	(return (+ 3 temp0))
)

(procedure (localproc_0042 &tmp temp0)
	(Print
		@str
		#at
		160
		10
		#title
		{You say...}
		#width
		140
		#mode
		1
		#time
		(= temp0 (PrintDelay @str))
		#dispose
	)
	(return (+ 3 temp0))
)

(instance aWine of View
	(properties
		y 115
		x 162
		view 480
		loop 4
		cel 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 setCel: 3 ignoreActors: stopUpd:)
	)
)

(instance aDoor of Prop
	(properties
		y 65
		x 159
		view 480
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 3 ignoreActors: stopUpd:)
	)
)

(instance aPanties of View
	(properties
		y 131
		x 63
		view 480
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aBra of View
	(properties
		y 131
		x 63
		view 480
		loop 1
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aPantyhose of View
	(properties
		y 141
		x 63
		view 480
		loop 1
		cel 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aDress of Prop
	(properties
		y 107
		x 39
		view 480
		loop 2
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 11
			setCel: (if (InRoom 17 484) 0 else 255)
			ignoreActors:
			stopUpd:
		)
	)
)

(instance atpTelescope of PV
	(properties
		y 50
		x 160
		view 480
		loop 3
		priority 1
		signal $4000
	)
)

(instance rm480 of Rm
	(properties
		picture 480
		south 470
	)
	
	(method (init)
		(gTheMusic fade:)
		(Load rsSCRIPT 969)
		(if (ego has: 13)
			(Load rsPIC 99)
			(Load rsVIEW 481)
			(Load rsSOUND 480)
			(Load rsSOUND 481)
			(Load rsSOUND 483)
		else
			(Load rsSOUND 9)
		)
		(super init:)
		(User canInput: 0 mapKeyToDir: 0)
		(aWine init:)
		(aDoor init:)
		(aPanties init:)
		(aBra init:)
		(aPantyhose init:)
		(aDress init:)
		(addToPics add: atpTelescope doit:)
		(self setScript: RoomScript)
		(aPatti init:)
		(NormalEgo)
		(HandsOff)
		(ego posn: 159 188 loop: 3 observeControl: 16384 init:)
		(if (ego has: 13)
			(RoomScript changeState: 1)
		else
			(RoomScript changeState: 43)
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (>= state 10) (theGame setSpeed: 6))
	)
	
	(method (changeState newState &tmp egoX egoY)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1 (= seconds 3))
			(2
				(if (Btst 66) (Print 480 14) else (Print 480 15))
				(= seconds 3)
			)
			(3
				(if (Btst 66)
					(Print 480 16)
				else
					(Bset 66)
					(Print 480 17)
				)
				(= gCurRoomNum 0)
				(NormalEgo)
				(ego observeControl: 16384)
			)
			(4
				(HandsOff)
				(Ok)
				(theGame changeScore: 500)
				(Print 480 18)
				(self cue:)
			)
			(5
				(cond 
					((>= (ego y?) 140) (= egoX 158) (= egoY 140))
					((& (ego onControl: 1) $0800) (= egoX (ego x?)) (= egoY 140) (-- state))
					((& (ego onControl: 1) $1000) (= egoX (ego x?)) (= egoY 140) (-- state))
					((& (ego onControl: 1) $0200)
						(= egoX 95)
						(if (> (ego x?) 95)
							(= egoY (ego y?))
						else
							(= egoY 120)
						)
						(-- state)
					)
					((& (ego onControl: 1) $0400)
						(= egoX 231)
						(if (< (ego x?) 231)
							(= egoY (ego y?))
						else
							(= egoY 120)
						)
						(-- state)
					)
					(else (Print 480 19))
				)
				(ego setMotion: MoveTo egoX egoY self)
			)
			(6
				(PutInRoom 13 484)
				((Inv at: 13) view: 28)
				(Format ((Inv at: 13) name?) 480 20)
				(ego loop: 3)
				(aWine setCel: 4 stopUpd:)
				(= cycles 11)
			)
			(7
				(ego setMotion: MoveTo 194 140 self)
			)
			(8
				(ego setMotion: MoveTo 194 132 self)
			)
			(9
				(= currentStatus (theGame setSpeed: 6))
				(gTheMusic number: 480 loop: -1 play:)
				(SL disable:)
				(TheMenuBar hide: state: 0)
				(Bset 5)
				(Bset 4)
				(Format @str 480 21)
				(= seconds (localproc_0042))
				(ego loop: 2)
				(Display 480 22 dsCOORD 0 180 dsCOLOR 4 dsFONT userFont)
			)
			(10
				(ego
					posn: 176 92
					cycleSpeed: 1
					view: 481
					setPri: 8
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(11
				(Format @str 480 23)
				(= seconds (localproc_000c))
			)
			(12
				(Format @str 480 24)
				(= seconds (localproc_0042))
			)
			(13
				(Format @str 480 25)
				(= seconds (localproc_000c))
			)
			(14
				(ego loop: 1 cel: 0 setCycle: CT 7 1 self)
			)
			(15
				(aWine setCel: 3 stopUpd:)
				(ego setCycle: End self)
			)
			(16
				(Format @str 480 26)
				(= seconds (localproc_0042))
				(ego loop: 2 cel: 0 setCycle: Fwd)
			)
			(17
				(Format @str 480 27)
				(= seconds (localproc_000c))
				(ego loop: 3 cel: 0 setCycle: Fwd)
			)
			(18
				(aPatti setCycle: End)
				(ego loop: 4 cel: 0 setCycle: CT 5 1 self)
			)
			(19
				(aWine setCel: 2 stopUpd:)
				(ego setCycle: End self)
			)
			(20
				(aPatti loop: 1 cel: 0)
				(aWine setCel: 1 stopUpd:)
				(ego loop: 5 cel: 0 setCycle: End self)
			)
			(21
				(aPatti setCycle: End)
				(ego view: 483 loop: 0 cel: 0 setCycle: End)
				(= cycles 22)
			)
			(22
				(aPatti setCycle: Beg)
				(ego setCycle: Beg)
				(= cycles 22)
				(if (> 3 (++ local0)) (= state (- state 2)))
			)
			(23
				(Format @str 480 28)
				(= seconds (- (localproc_0042) 1))
			)
			(24
				(Format @str 480 29)
				(= seconds (localproc_000c))
				(ego hide:)
				(aPatti loop: 2 cel: 0 setCycle: End)
				(gTheMusic number: 481 loop: 2 play:)
			)
			(25
				(aPatti setCycle: CT 3 -1)
				(= cycles 22)
			)
			(26
				(Format @str 480 30)
				(= seconds (localproc_0042))
			)
			(27
				(Format @str 480 31)
				(= seconds (localproc_000c))
			)
			(28
				(aPatti setCycle: End)
				(= cycles 44)
			)
			(29
				(aPatti setCycle: CT 3 -1)
				(= cycles 8)
			)
			(30
				(Format @str 480 32)
				(= seconds (localproc_000c))
			)
			(31
				(Format @str 480 33)
				(= seconds (localproc_0042))
			)
			(32
				(aPatti loop: 3 cel: 0 setCycle: End)
				(= cycles 33)
			)
			(33
				(curRoom drawPic: 99 6)
				(cast eachElementDo: #hide)
				(systemWindow color: 12 back: 8)
				(gTheMusic number: 483 loop: 2 play:)
				(= cycles 30)
			)
			(34
				(Format @str 480 34)
				(= seconds (localproc_000c))
			)
			(35
				(Format @str 480 35)
				(= seconds (localproc_000c))
			)
			(36
				(Format @str 480 36)
				(= seconds (- (localproc_000c) 1))
			)
			(37
				(Format @str 480 37)
				(= seconds (- (localproc_000c) 1))
			)
			(38
				(Format @str 480 38)
				(= seconds (- (localproc_000c) 1))
			)
			(39
				(Format @str 480 39)
				(= seconds (- (localproc_000c) 2))
			)
			(40
				(Format @str 480 40)
				(= seconds (- (localproc_000c) 2))
			)
			(41
				(Format @str 480 41)
				(Print @str #time (PrintDelay @str))
				(Format @str 480 42)
				(Print @str #time (PrintDelay @str))
				(= seconds (+ 3 (PrintDelay @str)))
			)
			(42 (curRoom newRoom: 481))
			(43
				(ego setMotion: MoveTo (ego x?) 180 self)
			)
			(44
				(Print 480 43 #at 10 5 #width 290)
				(Print 480 44 #at -1 144)
				(= seconds 3)
			)
			(45
				(Print 480 45 #at 10 5 #width 290)
				(gTheMusic number: 9 loop: 1 play: self)
				(Printf 480 46 filthStr)
			)
			(46
				(ego setMotion: MoveTo (ego x?) 192)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event claimed?) 0)
				(== (event message?) KEY_F8)
				(>= state 9)
			)
			(Print 480 0)
			(Bset 69)
			(curRoom newRoom: 484)
		)
		(if (event claimed?) (return))
		(cond 
			(
				(or
					(Said 'make/enjoy')
					(Said 'bang')
					(Said 'go,(climb<in,on),(get<in)/bed')
					(Said 'naked')
					(Said 'get/naked')
					(Said 'drain,give,backdrop/beer')
					(Said 'wear/nothing')
				)
				(if
					(and
						(not (& (ego onControl: 1) $0200))
						(not (& (ego onControl: 1) $0400))
						(not (& (ego onControl: 1) $0800))
						(not (& (ego onControl: 1) $1000))
					)
					(NotClose)
				else
					(self changeState: 4)
				)
			)
			((Said 'address') (Print 480 1))
			((Said 'open/door') (Print 480 2))
			((Said 'get') (Print 480 3))
			((Said 'look>')
				(cond 
					((Said '/balcony,camp,bay,air,cup') (Print 480 4))
					((Said '/bed') (Print 480 5))
					((Said '/entertainer,babe') (Print 480 6))
					((Said '/cloth') (Print 480 7))
					((Said '/keyboard') (Print 480 8))
					((Said '/binocular') (Print 480 9))
					((Said '/burn') (Print 480 10))
					((Said '/bush') (Print 480 11))
					((Said '/buffet') (Print 480 12))
					((Said '[/area]') (Print 480 13))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(or
						(proc0_26 aPanties (event x?) (event y?))
						(proc0_26 aBra (event x?) (event y?))
						(proc0_26 aPantyhose (event x?) (event y?))
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 480 7))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 4)
						(< (event x?) 58)
						(> (event y?) 98)
						(< (event y?) 155)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 480 12))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 150)
						(< (event x?) 177)
						(> (event y?) 112)
						(< (event y?) 134)
					)
					(event claimed: 1)
					(switch theCursor
						(13
							(if
								(and
									(not (& (ego onControl: 1) $0200))
									(not (& (ego onControl: 1) $0400))
									(not (& (ego onControl: 1) $0800))
									(not (& (ego onControl: 1) $1000))
								)
								(NotClose)
							else
								(= gTheCursor 900)
								(theGame setCursor: 998 (HaveMouse))
								(self changeState: 4)
							)
						)
						(998 (Print 480 12))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 114)
						(< (event x?) 216)
						(> (event y?) 78)
						(< (event y?) 105)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 480 5))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 271)
						(< (event x?) 319)
						(> (event y?) 100)
						(< (event y?) 154)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 480 8))
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aPatti (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998 (Print 480 6))
						(997 (Print 480 1))
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aPatti of Prop
	(properties
		y 101
		x 151
		view 483
		loop 4
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 8 stopUpd:)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 590)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm590 0
)

(local
	[msgBuf 88]
	[titleBuf 22]
)
(instance rm590 of Rm
	(properties
		picture 590
	)
	
	(method (init)
		(Load rsVIEW 586)
		(Load rsVIEW 593)
		(Load rsVIEW 594)
		(Load rsSOUND 4)
		(Load rsSOUND 6)
		(Load rsSOUND 486)
		(Load rsPIC 99)
		(User canInput: 0 canControl: 0 mapKeyToDir: 0)
		(super init:)
		(addToPics add: atpRope doit:)
		(aLarry init:)
		(aCageBack init:)
		(aCageFront init:)
		(self setScript: RoomScript style: 7)
		((Inv at: 17) view: 33)
		(= global66 801)
		(NormalEgo 0 592)
		(ego
			ignoreActors:
			illegalBits: 0
			setLoop: 0
			cel: 0
			setPri: 6
			posn: 174 110
			init:
		)
		(proc0_28 110 215 55 112 10)
		(User canControl: 0 canInput: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCycle: End)
				(aLarry setCycle: End)
				(= seconds 2)
			)
			(1
				(Print 590 19 #at 10 10 #width 140 #dispose)
				(Print 590 20 #at 170 10 #width 140)
				(cls)
				(ego setLoop: 1 setCycle: Fwd)
				(aLarry setLoop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(2
				(Print 590 21 #at 10 10 #width 140 #dispose)
				(Print 590 22 #at 170 10 #width 140)
				(cls)
				(ego setLoop: 2 cel: 0 setCycle: End self)
				(aLarry setLoop: 2 cel: 0 setCycle: End self)
			)
			(3)
			(4
				(ego view: 593 setLoop: 1 cel: 0 setCycle: End)
				(aLarry hide:)
				(= seconds 4)
			)
			(5 (ego setCycle: Beg self))
			(6
				(ego view: 592 setLoop: 2 setCel: 255 setCycle: Beg self)
				(aLarry
					view: 591
					setLoop: 2
					setCel: 255
					setCycle: Beg self
					show:
				)
			)
			(7)
			(8
				(ego setLoop: 1 setCycle: Fwd)
				(aLarry setLoop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(9
				(ego setCel: 0)
				(aLarry setCel: 0)
				(gTheMusic fade:)
				(Print 590 23 #at 10 5 #width 290)
				(gTheMusic number: 486 loop: -1 play:)
				(Print 590 24 #at -1 10)
				(Print 590 25 #at 10 5 #width 290)
				(Print 590 26 #at 10 5 #width 290)
				(Print 590 27 #at 10 5 #width 290)
				(Print 590 28 #at 10 5 #width 290)
				(Print 590 29 #at -1 10)
				(Print 590 30 #at -1 10)
				(User canInput: 1)
				(User canControl: 0)
				(HandsOn)
				(theGame setCursor: 998 (HaveMouse))
				(= theCursor 998)
				(= seconds 15)
			)
			(10
				(gTheMusic number: 6 loop: -1 play:)
				(aDoctor init: setCycle: End self)
			)
			(11
				(aDoctor cycleSpeed: 0 setLoop: 2 setCycle: Fwd)
				(= cycles 33)
			)
			(12
				(Print 590 31 #at -1 10)
				(= cycles 33)
			)
			(13
				(aDoctor setLoop: 1)
				(= cycles 33)
			)
			(14
				(aDoctor setLoop: 2)
				(aBubblesFront init:)
				(= cycles 33)
			)
			(15
				(Print 590 32 #at -1 10)
				(ego
					view: 593
					cycleSpeed: 3
					setLoop: 2
					cel: 0
					setCycle: End self
				)
				(aLarry hide:)
			)
			(16
				(aBubblesFront setLoop: 3)
				(= seconds 3)
			)
			(17
				(aBubblesFront setLoop: 5)
				(aBubblesRear init:)
				(ego view: 593 setLoop: 3 cel: 0 setCycle: End self)
			)
			(18
				(ego posn: 164 114 setLoop: 0 setPri: 13)
				(aCageFront hide:)
				(aCageBack hide:)
				(= seconds 3)
			)
			(19
				(aLarry
					posn: 163 53
					view: 590
					setLoop: 6
					setMotion: MoveTo 163 199
					setPri: 14
					show:
				)
				(ego cycleSpeed: 0 setMotion: MoveTo 164 161 self)
			)
			(20
				(ego setMotion: MoveTo 164 231)
				(aDoctor
					setLoop: 0
					setCel: 255
					cycleSpeed: 1
					setCycle: Beg
				)
				(= seconds 5)
			)
			(21
				(aDoctor hide:)
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 595
					register: (Format @msgBuf 590 33)
					next: (Format @titleBuf 590 34)
				)
			)
			(22)
			(23
				(HandsOff)
				(Ok)
				(theGame changeScore: 500)
				(Print 590 35 #at 10 5 #width 290)
				(aLarry loop: 1 cel: 0 stopUpd:)
				(= cycles (= seconds 0))
				(ego
					cycleSpeed: 1
					view: 594
					setLoop: 0
					cel: 0
					setCycle: End self
				)
			)
			(24
				(ego setLoop: 1 cel: 0 setCycle: End self)
			)
			(25
				(ego setLoop: 2 cel: 0 setCycle: End self)
			)
			(26
				(aBubblesFront
					init:
					view: 594
					setLoop: 5
					cel: 0
					cycleSpeed: 0
					setPri: 8
					posn: 169 72
					setCycle: Fwd
				)
				(ego setCycle: Beg self)
			)
			(27
				(ego setLoop: 3 cel: 0 setCycle: End self)
			)
			(28
				(aBubblesRear
					init:
					view: 594
					setLoop: 6
					cel: 0
					cycleSpeed: 1
					setPri: 5
					posn: 169 72
					setCycle: End self
				)
				(ego setCycle: Beg self)
			)
			(29)
			(30
				(aBubblesRear stopUpd:)
				(ego setLoop: 1 setCel: 255 setCycle: Beg)
				(= seconds 3)
			)
			(31
				(Print 590 36 #at 10 5 #width 290)
				(Print 590 37 #at 10 5 #width 290)
				(= cycles 22)
			)
			(32
				(Print 590 38 #at 10 5 #width 290)
				(= cycles 22)
			)
			(33
				(ego hide:)
				(aBubblesRear
					cycleSpeed: 1
					setLoop: 4
					cel: 0
					setCycle: End self
				)
			)
			(34
				(Format @msgBuf 590 39 filthStr)
				(Print @msgBuf #at -1 10)
				(orchidSeconds number: 4 loop: 1 play:)
				(Print 590 40 #at -1 10)
				(= cycles 11)
			)
			(35
				(aLarry
					cycleSpeed: 0
					moveSpeed: 0
					view: 594
					setLoop: 7
					cel: 0
					setCycle: Fwd
					setStep: 2 2
					setPri: 10
					posn: 188 104
					setMotion: MoveTo 156 104 self
				)
			)
			(36
				(aLarry hide:)
				(aBubblesRear
					view: 594
					setLoop: 8
					cel: 0
					setCycle: End self
				)
			)
			(37
				(gTheMusic stop:)
				(orchidSeconds number: 4 loop: 1 play:)
				(aBubblesRear setLoop: 6 setCel: 255 setCycle: Beg self)
			)
			(38
				(aBubblesFront hide:)
				(= cycles 22)
			)
			(39
				(curRoom drawPic: 99 6)
				(cast eachElementDo: #hide)
				(= seconds 3)
			)
			(40 (curRoom newRoom: 600))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			(
			(or (Said 'draw,make/door') (Said 'draw,use/marker'))
				(if (not (ego has: 18))
					(Print 590 0)
				else
					(self changeState: 23)
				)
			)
			((Said 'throw,make,use/magic,spell') (if (ego has: 18) (Print 590 1) else (Print 590 2)))
			((Said 'unfasten,grab,carve/hemp,bamboo') (Print 590 3))
			((Said 'throw') (Print 590 4))
			((Said 'use') (Print 590 5))
			((Said 'get') (Print 590 6))
			((or (Said 'enjoy') (Said '/enjoy')) (Print 590 7))
			((Said 'bend/bar') (Print 590 8))
			((Said 'attack') (Print 590 9))
			((Said 'throw,drag/cage') (Print 590 10))
			((Said 'look>')
				(cond 
					((Said '/hemp') (Print 590 11))
					((Said '/man,larry') (Print 590 12))
					(
					(and (cast contains: aDoctor) (Said '/cannibal')) (Print 590 13))
					((Said '/camp') (Print 590 14))
					((Said '/cage,bar') (Print 590 15))
					((Said '/dope') (Print 590 16))
					((Said '/bamboo,cage') (Print 590 17))
					((Said '[/area]') (Print 590 18))
				)
			)
			((== (event type?) evMOUSEBUTTON)
				(if (& (event modifiers?) emSHIFT)
					(if (== theCursor 992)
						(event claimed: 1)
						(theGame setCursor: 998 (HaveMouse))
						(= theCursor 998)
					)
					(if (== theCursor 999) (event claimed: 1))
					(switch theCursor
						(gTheCursor
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(991
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(992
							(theGame setCursor: 998 (HaveMouse))
							(= theCursor 998)
							(event claimed: 1)
						)
						(999
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(993
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(996
							(theGame setCursor: 994 (HaveMouse))
							(event claimed: 1)
						)
						(998
							(theGame setCursor: 995 (HaveMouse))
							(event claimed: 1)
						)
						(995
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(994
							(if
								(or
									(== gTheCursor 900)
									(== gTheCursor 994)
									(== gTheCursor 666)
									(== gTheCursor 992)
									(== gTheCursor 993)
									(== gTheCursor 999)
								)
								(theGame setCursor: 998 (HaveMouse))
							else
								(theGame setCursor: gTheCursor (HaveMouse))
								(= theCursor gTheCursor)
							)
							(event claimed: 1)
						)
					)
				else
					(if (== theCursor 999)
						(theGame setCursor: 998 (HaveMouse))
						(event claimed: 1)
					)
					(if (== theCursor 992)
						(event claimed: 1)
						(theGame setCursor: 998 (HaveMouse))
						(= theCursor 998)
						(switch theCursor
							(gTheCursor
								(theGame setCursor: 998 (HaveMouse))
								(event claimed: 1)
							)
							(991
								(theGame setCursor: 998 (HaveMouse))
								(event claimed: 1)
							)
							(992
								(theGame setCursor: 998 (HaveMouse))
								(= theCursor 998)
								(event claimed: 1)
							)
							(999
								(theGame setCursor: 998 (HaveMouse))
								(= theCursor 998)
								(event claimed: 1)
							)
							(993
								(theGame setCursor: 996 (HaveMouse))
								(event claimed: 1)
							)
							(996
								(theGame setCursor: 994 (HaveMouse))
								(event claimed: 1)
							)
							(998
								(theGame setCursor: 995 (HaveMouse))
								(event claimed: 1)
							)
							(995
								(theGame setCursor: 996 (HaveMouse))
								(event claimed: 1)
							)
							(994
								(if
									(or
										(== gTheCursor 900)
										(== gTheCursor 994)
										(== gTheCursor 666)
										(== gTheCursor 992)
										(== gTheCursor 993)
										(== gTheCursor 999)
									)
									(theGame setCursor: 991 (HaveMouse))
								else
									(theGame setCursor: gTheCursor (HaveMouse))
									(= theCursor gTheCursor)
								)
								(event claimed: 1)
							)
						)
					)
					(if
						(and
							(> (event x?) 110)
							(< (event x?) 215)
							(> (event y?) 55)
							(< (event y?) 112)
						)
						(event claimed: 1)
						(switch theCursor
							(18 (self changeState: 23))
							(998 (Print 590 17))
							(else  (event claimed: 0))
						)
					)
				)
			)
		)
	)
)

(instance atpRope of PV
	(properties
		y 53
		x 163
		view 590
		loop 6
		priority 2
		signal $4000
	)
)

(instance aCageFront of View
	(properties
		y 114
		x 164
		view 590
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 12 ignoreActors: stopUpd:)
	)
)

(instance aCageBack of View
	(properties
		y 114
		x 164
		view 590
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 4 stopUpd:)
	)
)

(instance aBubblesFront of Prop
	(properties
		y 160
		x 203
		view 590
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Fwd ignoreActors:)
	)
)

(instance aBubblesRear of Prop
	(properties
		y 160
		x 203
		view 590
		loop 4
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 setCycle: Fwd ignoreActors:)
	)
)

(instance aLarry of Act
	(properties
		y 110
		x 173
		view 591
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 illegalBits: 0 ignoreActors:)
	)
)

(instance aDoctor of Prop
	(properties
		y 72
		x 288
		view 586
		cycleSpeed 1
	)
)

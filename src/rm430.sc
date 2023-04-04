;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm430 0
)

(local
	actor4
	actor3
	actor5
	money
	actors
	actorXY
	theAMoney3
	theAMoney4
	theAMoney5
	local9
	theAActor1
	theAActor2
	theAActor3
	theAActor4
	theAActor5
	[local15 12] = [0 0 54 74 41 78 26 83 11 88 -4 93]
)
(instance rm430 of Rm
	(properties
		picture 430
		horizon 1
	)
	
	(method (init &tmp temp0)
		(= gameMinutes 1)
		(= gGCurRoomNum gCurRoomNum)
		(= gCurRoomNum curRoomNum)
		(HandsOff)
		(Load rsVIEW 432)
		(Load rsSOUND 431)
		(Load rsSOUND 13)
		(Load rsSOUND 10)
		(Load rsSCRIPT 991)
		(Load rsPIC 431)
		(gTheMusic number: 430 loop: -1 play:)
		(super init:)
		(aCurtain ignoreActors: init:)
		(aActor1 ignoreActors: init:)
		(aActor2 ignoreActors: init:)
		(aActor3 ignoreActors: init:)
		(aActor4 ignoreActors: init:)
		(aActor5 ignoreActors: init:)
		(= currentStatus (theGame setSpeed: 6))
		(= theAActor1 aActor1)
		(= theAActor2 aActor2)
		(= theAActor3 aActor3)
		(= theAActor4 aActor4)
		(= theAActor5 aActor5)
		(aMoney1 ignoreActors: init:)
		(aMoney2 ignoreActors: init:)
		(aMoney3 ignoreActors: init:)
		(aMoney4 ignoreActors: init:)
		(aMoney5 ignoreActors: init:)
		(= actors aMoney1)
		(= actorXY aMoney2)
		(= theAMoney3 aMoney3)
		(= theAMoney4 aMoney4)
		(= theAMoney5 aMoney5)
		(= actor3 aActor3)
		(= actor4 aActor4)
		(= actor5 aActor5)
		(ego posn: 999 999 init: hide:)
		(= temp0 1)
		(while (<= temp0 5)
			([local9 temp0]
				view: 433
				posn: [local15 (* temp0 2)] [local15 (+ 1 (* temp0 2))]
				setPri: 3
				setStep: 1 1
			)
			(++ temp0)
		)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0)
		(ChangeScriptState self newState 1 12)
		(switch (= state newState)
			(0
				(theAActor1 setCycle: End self)
			)
			(1
				(theAActor1 cel: 0 setCycle: End)
				(theAActor2 setCycle: End self)
			)
			(2
				(theAActor1 cel: 0 setCycle: End)
				(theAActor2 cel: 0 setCycle: End)
				(theAActor3 setCycle: End self)
			)
			(3
				(theAActor1 cel: 0)
				(theAActor2 cel: 0 setCycle: End)
				(theAActor3 cel: 0 setCycle: End)
				(theAActor4 setCycle: End self)
			)
			(4
				(Print 430 0 #at 10 144 #width 290 #dispose)
				(theAActor2 cel: 0)
				(theAActor3 cel: 0 setCycle: End)
				(theAActor4 cel: 0 setCycle: End)
				(theAActor5 setCycle: End self)
			)
			(5
				(theAActor3 cel: 0)
				(theAActor4 cel: 0 setCycle: End)
				(theAActor5 cel: 0 setCycle: End self)
			)
			(6
				(theAActor4 cel: 0)
				(theAActor5 setCycle: End self)
			)
			(7
				(theAActor5 cel: 0 setCycle: End self)
			)
			(8
				(theAActor4 cel: 0 setCycle: End self)
			)
			(9
				(theAActor3 cel: 0 setCycle: End self)
			)
			(10
				(theAActor2 cel: 0 setCycle: End self)
			)
			(11
				(theAActor1 cel: 0 setCycle: End self)
			)
			(12
				(theAActor2 setCycle: End self)
			)
			(13
				(theAActor3 setCycle: End self)
			)
			(14
				(theAActor4 setCycle: End self)
			)
			(15
				(theAActor5 setCycle: End self)
			)
			(16
				(theAActor4 setCycle: End self)
			)
			(17
				(theAActor3 setCycle: End self)
			)
			(18
				(theAActor2 setCycle: End self)
			)
			(19
				(cls)
				(theAActor1 setCycle: End self)
			)
			(20
				(theAActor1 setCycle: End self)
				(theAActor2 setCycle: Fwd)
			)
			(21
				(theAActor1 setCycle: End self)
				(theAActor3 setCycle: Fwd)
			)
			(22
				(theAActor1 setCycle: End self)
				(theAActor4 setCycle: Fwd)
			)
			(23
				(theAActor1 setCycle: Fwd)
				(theAActor5 setCycle: Fwd)
				(= cycles 55)
			)
			(24
				(= temp0 1)
				(while (<= temp0 5)
					([local9 temp0] setMotion: MoveTo 119 52)
					(++ temp0)
				)
				(= cycles 66)
			)
			(25
				(gTheMusic fade:)
				(= cycles 66)
			)
			(26
				(gTheMusic number: 10 loop: -1 play:)
				(Print 430 1 #at 10 5 #width 290)
				(theAActor1 dispose:)
				(theAActor2 dispose:)
				(actor3 view: 430 setLoop: 3 posn: 167 97 setPri: 2 hide:)
				(actor4
					view: 430
					setLoop: 4
					posn: 148 163
					setPri: 3
					hide:
				)
				(actor5
					view: 432
					setLoop: 0
					setCel: 0
					posn: 149 140
					setPri: 4
					hide:
				)
				(= cycles 22)
			)
			(27
				(curRoom drawPic: 431 8)
				(aSpotlight
					loop: 1
					setCel: 1
					posn: 160 45
					setPri: 1
					ignoreActors:
					init:
					stopUpd:
				)
				(aFloor
					loop: 2
					setCel: 1
					posn: 152 102
					setPri: 6
					ignoreActors:
					init:
					stopUpd:
				)
				(actor4 show:)
				(actor5 show:)
				(= cycles 11)
			)
			(28
				(actor3 show: setMotion: MoveTo 149 97 self)
			)
			(29
				(actor3 stopUpd:)
				(actor5 setMotion: MoveTo 149 86)
				(actor4 setMotion: MoveTo 148 109 self)
			)
			(30
				(actor4 stopUpd:)
				(gTheMusic number: 431 loop: -1 play:)
				(actor5 setLoop: 3 setCycle: Fwd)
				(= cycles 33)
			)
			(31
				(actor5 setLoop: 4)
				(= cycles 33)
			)
			(32
				(actor5 cel: 0 setCycle: End self)
			)
			(33
				(actor5 setLoop: 1 setCycle: Fwd)
				(Print 430 2 #at 10 144 #width 290 #dispose)
				(= temp0 1)
				(while (<= temp0 5)
					([money temp0] setScript: (MoneyScript new:))
					(++ temp0)
				)
				(self cue:)
			)
			(34
				(= cycles (* 2 (NumCels actor5)))
				(if (> speed 1)
					(theGame setSpeed: (-- speed))
					(-- state)
				)
			)
			(35
				(cls)
				(actor5 setCycle: Fwd)
				(= cycles 261)
			)
			(36
				(if 0
					(self cue:)
				else
					(aSpotlight setCycle: Fwd)
					(aFloor setCycle: Fwd)
					(= cycles 261)
				)
			)
			(37
				(aSpotlight setCel: 1 stopUpd:)
				(aFloor setCel: 1 stopUpd:)
				(self cue:)
			)
			(38
				(= cycles (* 2 (NumCels actor5)))
				(if (< speed 6)
					(theGame setSpeed: (++ speed))
					(-- state)
				)
			)
			(39
				(actor5 setLoop: 2 setCycle: End self)
			)
			(40
				(actor5 setCel: 255)
				(gTheMusic fade:)
				(= seconds 4)
			)
			(41
				(Print 430 3 #at 10 5 #width 290)
				(gTheMusic number: 13 loop: -1 play:)
				(actor4 setMotion: MoveTo 148 163)
				(actor5 setMotion: MoveTo 149 140 self)
				(= temp0 1)
				(while (<= temp0 5)
					(([money temp0] script?) changeState: 2)
					(++ temp0)
				)
			)
			(42
				(gTheMusic fade:)
				(Print 430 4 #at 10 5 #width 290)
				(actor3 setMotion: MoveTo 167 97 self)
			)
			(43
				(curRoom drawPic: 430 8)
				(actor3 dispose:)
				(aSpotlight dispose:)
				(aFloor dispose:)
				(Animate (cast elements?) 0)
				(Print 430 5 #at 10 5 #width 290)
				(= seconds 3)
			)
			(44
				(Print 430 6)
				(Print 430 7 #at -1 144)
				(= seconds 2)
			)
			(45
				(theGame setSpeed: currentStatus)
				(= gCurRoomNum gGCurRoomNum)
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance aActor1 of Act
	(properties)
)

(instance aActor2 of Act
	(properties)
)

(instance aActor3 of Act
	(properties)
)

(instance aActor4 of Act
	(properties)
)

(instance aActor5 of Act
	(properties)
)

(instance aSpotlight of Prop
	(properties
		y 999
		x 999
		view 430
	)
)

(instance aFloor of Prop
	(properties
		y 999
		x 999
		view 430
	)
)

(instance aCurtain of Prop
	(properties
		y 63
		x 15
		view 430
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 2 ignoreActors: stopUpd:)
	)
)

(instance aMoney1 of Act
	(properties)
)

(instance aMoney2 of Act
	(properties)
)

(instance aMoney3 of Act
	(properties)
)

(instance aMoney4 of Act
	(properties)
)

(instance aMoney5 of Act
	(properties)
)

(instance MoneyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client view: 430 setLoop: 7 setCycle: Fwd setStep: 7 7)
				(= cycles (Random 1 9))
			)
			(1
				(client
					posn: (Random 8 290) (Random 144 157)
					setMotion: JumpTo (actor5 x?) (- (actor5 y?) (Random 0 33)) self
				)
				(-- state)
			)
			(2
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

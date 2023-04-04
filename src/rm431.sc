;;; Sierra Script 1.0 - (do not remove this comment)
(script# 431)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm431 0
)

(local
	actors
	veggies
	toX
	msgBuf
	titleBuf
	theAActor5
	local6
	theAActor1
	theAActor2
	theAActor3
	theAActor4
	theAActor5_2
	local12 =  999
	local13 =  113
	local14 =  127
	local15 =  141
	[local16 2] = [155 169]
	local18 =  183
	[local19 2] = [197 999]
	[local21 40]
	[local61 22]
)
(instance rm431 of Rm
	(properties
		picture 430
		horizon 1
	)
	
	(method (init &tmp temp0)
		(Load rsSOUND 10)
		(Load rsSOUND 433)
		(Load rsSOUND 432)
		(Load rsSCRIPT 991)
		(Load rsPIC 99)
		(Load rsFONT 7)
		(super init:)
		(self setScript: RoomScript)
		(aCurtain ignoreActors: init:)
		(aActor1 ignoreActors: init:)
		(aActor2 ignoreActors: init:)
		(aActor3 ignoreActors: init:)
		(aActor4 ignoreActors: init:)
		(aActor5 ignoreActors: init:)
		(= currentStatus (theGame setSpeed: 6))
		(= veggies aActor1)
		(= toX aActor2)
		(= msgBuf aActor3)
		(= titleBuf aActor4)
		(= theAActor5 aActor5)
		(= theAActor1 aActor1)
		(= theAActor2 aActor2)
		(= theAActor3 aActor3)
		(= theAActor4 aActor4)
		(= theAActor5_2 aActor5)
		(NormalEgo)
		(ego
			view: 431
			setLoop: 0
			setCycle: Walk
			setStep: 2 1
			setCycle: Walk
			setPri: 1
			posn: 16 75
			ignoreActors:
			init:
		)
		(HandsOff)
		(RoomScript changeState: 1)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if
			(and
				(== -1 (gTheMusic prevSignal?))
				(== (gTheMusic number?) 432)
			)
			(self changeState: 22)
		)
		(if (== gCurRoomNum 431)
			(cond 
				((& (ego onControl: 1) $0002) (= temp0 1))
				((& (ego onControl: 1) $0004) (= temp0 2))
				((& (ego onControl: 1) $0008) (= temp0 3))
				((& (ego onControl: 1) $0010) (= temp0 4))
				((& (ego onControl: 1) $0020) (= temp0 5))
				((& (ego onControl: 1) $0040) (= temp0 6))
				(else (= temp0 0))
			)
			(ego setLoop: temp0)
			(aFloor
				posn: [local12 temp0] 97
				setCel:
					(aSpotlight
						posn: [local12 temp0] 52
						setCel: (if (< global87 39) (return) else 0)
					)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(ChangeScriptState self newState 1 12)
		(switch (= state newState)
			(0)
			(1
				(Print 431 4 #at 10 5 #width 290)
				(aCurtain setCycle: End self)
				(= seconds 3)
			)
			(2)
			(3
				(aCurtain stopUpd:)
				(Print (Format @local21 431 5 filthStr) #font 7)
				(Print 431 6)
				(Print 431 7)
				(= seconds 3)
			)
			(4
				(Print 431 8 #at 10 5 #width 290)
				(Print 431 9)
				(Print 431 10 #at -1 144)
				(= seconds 3)
			)
			(5
				(Print 431 11 #at -1 10)
				(= seconds 3)
			)
			(6
				(Print 431 12 #at -1 10)
				(Print 431 13)
				(ego setPri: -1 setMotion: MoveTo 36 95 self)
			)
			(7
				(Print 431 14)
				(= gCurRoomNum 432)
				(theGame setCursor: 998 (HaveMouse))
				(HandsOn)
				(User canInput: 1)
				(gTheMusic stop:)
				(ego cel: 0)
				(aCurtain setCycle: Beg self)
			)
			(8
				(aCurtain stopUpd:)
				(= seconds 3)
			)
			(9
				(Print 431 15 #at 10 5 #width 290)
				(= seconds 10)
			)
			(10
				(gTheMusic number: 433 loop: -1 play:)
				(Print 431 16 #at -1 10)
				(Print 431 17 #at -1 144)
				(Print 431 18)
				(= seconds 3)
			)
			(11
				(Print 431 19 #at -1 10)
				(Print 431 20 #at -1 144)
				(= cycles (Random 2 8))
			)
			(12
				(HandsOff)
				(= temp0 1)
				(while (<= temp0 5)
					([local6 temp0]
						view: 430
						setCycle: Fwd
						setStep: 6 6
						setScript: (VeggieScript new:)
					)
					(++ temp0)
				)
				(= seconds 8)
			)
			(13
				(Print 431 21 #dispose #at 10 5 #width 290)
				(= seconds 3)
			)
			(14
				(cls)
				(= temp0 1)
				(while (<= temp0 5)
					(([local6 temp0] script?) changeState: 2)
					(++ temp0)
				)
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 437
					register: (Format @local21 431 22)
					next: (Format @local61 431 23)
				)
			)
			(15
				(= gCurRoomNum 431)
				(HandsOff)
				(Ok)
				(theGame changeScore: 43)
				(Print 431 24 #at 10 5 #width 290)
				(aSpotlight setLoop: 5 setPri: 14 ignoreActors: init:)
				(aFloor setLoop: 6 setPri: 7 ignoreActors: init:)
				(ego setMotion: MoveTo local18 95 self)
				(= cycles 4)
			)
			(16
				(gTheMusic number: 432 loop: 1 play: self)
			)
			(17
				(ego setMotion: MoveTo local13 95 self)
			)
			(18
				(Print 431 25 #at 10 5 #width 290 #dispose)
				(veggies setScript: (MoneyScript new:))
				(ego setMotion: MoveTo local15 95 self)
			)
			(19
				(toX setScript: (MoneyScript new:))
				(msgBuf setScript: (MoneyScript new:))
				(ego setStep: 1 1 setMotion: MoveTo local18 95 self)
			)
			(20
				(cls)
				(Print 431 26 #at 10 5 #width 290 #time 6 #dispose)
				(titleBuf setScript: (MoneyScript new:))
				(theAActor5 setScript: (MoneyScript new:))
				(= register 0)
				(self cue:)
			)
			(21
				(ego setMotion: MoveTo (Random local13 local18) 95 self)
				(/ global87 10)
				(if (>= 2 (++ register)) (-- state))
			)
			(22
				(gTheMusic loop: 1 play:)
				(curRoom drawPic: 99 6)
				(cast eachElementDo: #hide)
				(= temp0 1)
				(while (<= temp0 5)
					(([actors temp0] script?) changeState: 2)
					(++ temp0)
				)
				(= cycles 20)
			)
			(23
				(gTheMusic fade:)
				(Print 431 27)
				(Print 431 28)
				(Print 431 29)
				(Print 431 30 #at 10 144 #width 290)
				(Load rsVIEW 708)
				(= global66 708)
				(= gCurRoomNum 11)
				(= gameMinutes 6)
				(= global94 500)
				(Format ((Inv at: 6) name?) 431 31)
				((Inv at: 6) view: 24)
				(ego get: 6)
				(theGame setSpeed: currentStatus)
				(curRoom newRoom: 420)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			(
			(or (Said '/naked,dance') (Said 'naked,dance')) (self changeState: 15))
			((Said 'address') (Print 431 0) (Print 431 1))
			(
				(and
					(Said 'look>')
					(Said '[/area,backstage,couple,man,babe]')
				)
				(Print 431 2)
				(Print 431 3 #at -1 144)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if (== gCurRoomNum 432)
					(event claimed: 1)
					(if (== theCursor 992)
						(switch theCursor
							(992
								(theGame setCursor: 998 (HaveMouse))
								(event claimed: 1)
							)
							(994
								(theGame setCursor: 998 (HaveMouse))
								(event claimed: 1)
							)
							(997
								(theGame setCursor: 995 (HaveMouse))
								(event claimed: 1)
							)
							(998
								(theGame setCursor: 997 (HaveMouse))
								(event claimed: 1)
							)
							(995
								(theGame setCursor: 994 (HaveMouse))
								(event claimed: 1)
							)
							(else  (event claimed: 0))
						)
					)
				)
				(if
					(and
						(> (event x?) 66)
						(< (event x?) 211)
						(> (event y?) 86)
						(< (event y?) 117)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(Print 431 2)
							(Print 431 3 #at -1 144)
						)
						(995 (self changeState: 15))
						(else  (event claimed: 0))
					)
				)
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

(instance VeggieScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 1 9)))
			(1
				(client
					setLoop: (Random 8 9)
					posn: (Random 8 290) (Random 144 157)
					setMotion: JumpTo (ego x?) (- (ego y?) (Random 0 33)) self
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
					setMotion: JumpTo (ego x?) (- (ego y?) (Random 0 33)) self
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

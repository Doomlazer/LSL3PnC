;;; Sierra Script 1.0 - (do not remove this comment)
(script# 350)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm350 0
)
(synonyms
	(man attendant dave)
)

(local
	talkCount
)
(instance rm350 of Rm
	(properties
		picture 350
		east 305
		west 355
	)
	
	(method (init)
		(super init:)
		(addToPics
			add: atpChair1
			add: atpChair2
			add: atpChair3
			add: atpChair4
			add: atpTable1
			add: atpTable2
			add: atpTenPin
			doit:
		)
		(self setScript: RoomScript)
		(aDave init:)
		(aDoor init:)
		(aPins init:)
		(if (> global87 16)
			(aWaterfall setCycle: Fwd isExtra: 1 init:)
		)
		(cond 
			((== prevRoomNum 355)
				(HandsOff)
				(Load rsSOUND 350)
				(Load rsSOUND 351)
				(Load rsSOUND 11)
				(Load rsPIC 99)
				(aKen ignoreActors: setCycle: Walk setPri: 4 init:)
				(ego
					view: 351
					illegalBits: 0
					posn: 17 141
					setLoop: 0
					setCycle: Fwd
					setStep: 10 1
					init:
				)
				(DaveScript changeState: 3)
				(RoomScript changeState: 9)
			)
			((not (Btst 23))
				(HandsOff)
				(Bset 17)
				(Bset 19)
				(Bset 20)
				(Bset 21)
				(Bset 22)
				(Bset 23)
				(= gCurRoomNum 4)
				(ego illegalBits: 0 loop: 1 posn: 295 144 init:)
				(RoomScript changeState: 1)
			)
			(else
				(NormalEgo 1)
				(ego observeControl: 16384 posn: 295 144 init:)
			)
		)
		(gTheMusic number: 32 loop: global72 play:)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(ego setMotion: MoveTo 293 144 self)
			)
			(2
				(ego setMotion: MoveTo 281 144 self)
			)
			(3
				(Print 350 27)
				(ego setMotion: MoveTo 225 166 self)
			)
			(4
				(Print 350 28)
				(ego setMotion: MoveTo 123 166 self)
			)
			(5
				(Print 350 29)
				(Print 350 30)
				(ego setPri: -1 setMotion: MoveTo 53 138 self)
			)
			(6
				(ego loop: 1)
				(aDoor setCycle: End self)
			)
			(7
				(aDoor stopUpd:)
				(ego setMotion: MoveTo -10 141)
			)
			(8)
			(9
				(ego setMotion: MoveTo 192 141 self)
				(aKen setMotion: MoveTo 35 141)
				(orchidSeconds number: 350 loop: 1 play:)
			)
			(10
				(ego
					setPri: 10
					setLoop: 1
					cel: 0
					setCycle: End
					setMotion: MoveTo 255 137 self
				)
				(aPins setCycle: End)
				(gTheMusic stop:)
				(orchidSeconds stop: number: 351 loop: 1 play:)
			)
			(11
				(orchidSeconds fade:)
				(aPins stopUpd:)
				(ego stopUpd:)
				(= seconds 2)
			)
			(12
				(Printf 350 31 filthStr)
				(= seconds 2)
			)
			(13
				(Print 350 32)
				(aKen setMotion: MoveTo 0 141 self)
			)
			(14
				(aKen dispose:)
				(aDoor setCycle: Beg self)
				(Print 350 33)
				(Print 350 34)
				(= seconds 3)
			)
			(15
				(orchidSeconds number: 11 loop: 1 play:)
			)
			(16
				(curRoom drawPic: 99 6)
				(cast eachElementDo: #hide)
				(= cycles 20)
			)
			(17
				(Print 350 35)
				(= gCurRoomNum 4)
				(curRoom newRoom: 305)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'lie') (Print 350 0) (Print 350 1))
			((Said 'open/door') (Print 350 2) (Print 350 3 #at -1 144))
			((Said 'drink,get/water') (Print 350 4) (Print 350 5))
			((Said 'get') (Print 350 6))
			((Said 'give') (Print 350 7))
			((Said 'address/man,guard')
				(switch (++ talkCount)
					(1 (Print 350 8) (Print 350 9))
					(2
						(Print 350 10)
						(Print 350 11)
					)
					(else 
						(Print 350 12)
						(Print 350 13)
					)
				)
			)
			((or (Said '//job') (Said '/job')) (Print 350 14))
			((or (Said '/chairman') (Said '//chairman')) (Print 350 15) (Print 350 16 #at -1 144))
			((Said 'look>')
				(cond 
					((Said '/clit,skin,carpet,(head<skin),skin') (Print 350 17) (Print 350 18 #at -1 144))
					((Said '/barstool,book') (Printf 350 19 global82))
					((Said '/cup') (Print 350 20))
					((Said '/flower') (Print 350 21))
					((Said '/water,fountain,cascade') (Print 350 22))
					((Said '/buffet') (Print 350 23))
					((Said '/guard,man') (Print 350 24))
					((Said '/door') (Print 350 25))
					((Said '[/area]') (Print 350 26))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 99)
						(< (event x?) 187)
						(> (event y?) 130)
						(< (event y?) 153)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(Print 350 17)
							(Print 350 18 #at -1 144)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(or
						(proc0_26 atpTable1 (event x?) (event y?))
						(proc0_26 atpTable2 (event x?) (event y?))
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 350 23))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 277)
						(< (event x?) 318)
						(> (event y?) 86)
						(< (event y?) 150)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 286 181 self)
							(ego setMotion: MoveTo 321 140)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 27)
						(< (event x?) 41)
						(> (event y?) 83)
						(< (event y?) 131)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(Print 350 2)
							(Print 350 3 #at -1 144)
						)
						(995 (Print 350 25))
						(else  (event claimed: 0))
					)
				)
				(if
					(or
						(proc0_26 atpChair1 (event x?) (event y?))
						(proc0_26 atpChair2 (event x?) (event y?))
						(proc0_26 atpChair3 (event x?) (event y?))
						(proc0_26 atpChair4 (event x?) (event y?))
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(Print 350 0)
							(Print 350 1)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aDave (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(996
							(event type: 1 claimed: 1)
							(switch (++ talkCount)
								(1 (Print 350 8) (Print 350 9))
								(2
									(Print 350 10)
									(Print 350 11)
								)
								(else 
									(Print 350 12)
									(Print 350 13)
								)
							)
						)
						(998 (Print 350 24))
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aWaterfall (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(event type: 1 claimed: 1)
							(Print 350 22)
						)
						(995
							(Print 350 4)
							(Print 350 5)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 81)
						(< (event x?) 238)
						(> (event y?) 23)
						(< (event y?) 63)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 350 26))
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance atpChair1 of PV
	(properties
		y 124
		x 69
		view 350
		cel 1
	)
)

(instance atpChair2 of PV
	(properties
		y 126
		x 256
		view 350
		cel 2
	)
)

(instance atpChair3 of PV
	(properties
		y 168
		x 295
		view 350
		cel 3
	)
)

(instance atpChair4 of PV
	(properties
		y 168
		x 25
		view 350
	)
)

(instance atpTable1 of PV
	(properties
		y 108
		x 93
		view 350
		cel 4
	)
)

(instance atpTable2 of PV
	(properties
		y 109
		x 232
		view 350
		cel 5
	)
)

(instance aPins of Prop
	(properties
		y 132
		x 222
		view 350
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 ignoreActors: stopUpd:)
	)
)

(instance atpTenPin of PV
	(properties
		y 148
		x 239
		view 350
		loop 1
		priority 11
		signal $4000
	)
)

(instance aDave of Prop
	(properties
		y 169
		x 144
		view 353
	)
	
	(method (init)
		(super init:)
		(self
			cycleSpeed: 1
			setPri: 15
			setScript: DaveScript
			ignoreActors:
		)
	)
)

(instance DaveScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== 0 (aDave loop?))
			(aDave
				cel:
					(cond 
						((< 80 (ego x?)) 0)
						((< 140 (ego x?)) 1)
						((< 180 (ego x?)) 2)
						((< 240 (ego x?)) 3)
						(else 4)
					)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= cycles (Random 11 22)))
			(1
				(= seconds (Random 3 9))
				(switch (= temp0 (Random 0 5))
					(0
						(aDave loop: 1 setCycle: End self)
						(= seconds 0)
					)
					(1
						(aDave loop: 2 setCycle: End)
					)
					(else 
						(aDave loop: 0)
						(= state -1)
					)
				)
			)
			(2
				(aDave setCycle: Beg self)
				(= state -1)
			)
			(3
				(aDave loop: 0)
				(= seconds (= cycles 0))
			)
		)
	)
)

(instance aKen of Act
	(properties
		y 141
		x 17
		view 352
		illegalBits $0000
	)
)

(instance aWaterfall of Prop
	(properties
		y 103
		x 163
		view 350
		loop 3
	)
)

(instance aDoor of Prop
	(properties
		y 94
		x 29
		view 350
		loop 4
	)
	
	(method (init)
		(super init:)
		(self
			setCel: (if (== prevRoomNum 355) 255 else 0)
			ignoreActors:
			stopUpd:
		)
	)
)

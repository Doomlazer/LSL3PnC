;;; Sierra Script 1.0 - (do not remove this comment)
(script# 360)
(include sci.sh)
(use Main)
(use Door)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm360 0
)
(synonyms
	(man man man attendant robin)
)

(local
	talkCount
	local1
	local2
)
(instance rm360 of Rm
	(properties
		picture 360
		horizon 111
		north 390
		east 365
		south 300
	)
	
	(method (init)
		(if (ego has: 9)
			(Load rsVIEW 715)
			(Load rsVIEW 9)
			(Load rsSOUND 11)
		)
		(super init:)
		(addToPics
			add: atpRightCardHole
			add: atpLeftCardHole
			add: atpRearCardHole
			add: atpFatCity
			add: atpBboard
			add: atpShelves
			add: atpSumtin
			add: atpBlender
			doit:
		)
		(aTanBoothDoor init:)
		(aStudioDoor init:)
		(aLockerDoor init:)
		(if (not musicLoop) (aRobin init:))
		(self setLocales: 80 setScript: RoomScript)
		(NormalEgo)
		(cond 
			((== prevRoomNum 390)
				(ego posn: 198 122 loop: 2)
				(aStudioDoor close: locked: 1)
				(if (== gCurRoomNum 1)
					(HandsOff)
					(aBambi init:)
					(ego view: 720 posn: 191 122 illegalBits: 0 ignoreActors:)
					(gTheMusic number: 399 loop: global72 play:)
					(RoomScript changeState: 11)
				)
			)
			((== prevRoomNum 370) (ego posn: 27 173) (aLockerDoor close: locked: 1))
			((== prevRoomNum 365)
				(TheMenuBar draw: state: 1)
				(SL enable:)
				(ego loop: 1 posn: 294 177)
			)
			(else (ego posn: 159 186 loop: 3))
		)
		(ego init:)
		(User canInput: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (!= gCurRoomNum 1)
			(ego
				observeControl: (& (ego onControl:) $1000)
				ignoreControl: (& (ego onControl:) $0020)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 0))
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 60 160 self
				)
			)
			(2
				(ego view: 715 setLoop: 1 setCel: 0 setCycle: End self)
			)
			(3
				(NormalEgo)
				(HandsOff)
				(if (not (Btst 33))
					(Bset 33)
					(theGame changeScore: 3)
					(Print 360 24 #icon 9 0 0)
				)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 12 171 self
				)
				(aLockerDoor locked: 0 force: 1 open:)
			)
			(4
				(ego setMotion: MoveTo 0 171 self)
			)
			(5
				(aLockerDoor force: 1 close:)
				(orchidSeconds number: 11 loop: 1 play:)
				(ego setMotion: MoveTo -20 171 self)
			)
			(6 (curRoom newRoom: 370))
			(7
				(HandsOff)
				(Ok)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 217 124 self
				)
			)
			(8
				(ego view: 715 setCel: 0 setLoop: 2 setCycle: End self)
			)
			(9
				(if (not (Btst 34))
					(Bset 34)
					(theGame changeScore: 3)
					(Print 360 24 #icon 9 0 0)
				)
				(NormalEgo)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 197 118 self
				)
				(aStudioDoor locked: 0 force: 1 open:)
			)
			(10
				(ego setMotion: MoveTo 197 0)
			)
			(11 (= cycles 10))
			(12
				(aBambi setMotion: MoveTo 225 165 self)
				(= cycles 5)
			)
			(13
				(ego setMotion: MoveTo 225 165 self)
			)
			(14
				(Print 360 25)
				(Print 360 26)
				(aBambi setMotion: MoveTo 294 176)
			)
			(15
				(ego setMotion: MoveTo 280 179 self)
			)
			(16
				(aBambi
					cycleSpeed: 3
					setLoop: 3
					cel: 0
					setCycle: End self
				)
			)
			(17
				(theGame changeScore: 3)
				(= cycles 10)
			)
			(18
				(aBambi setCycle: Beg)
				(aTanBoothDoor setCycle: End self)
			)
			(19
				(aTanBoothDoor stopUpd:)
				(= cycles 20)
			)
			(20
				(Print 360 27)
				(= cycles 10)
			)
			(21
				(aBambi
					cycleSpeed: 0
					setCycle: Walk
					setLoop: 0
					setMotion: MoveTo 316 175 self
				)
			)
			(22
				(ego setMotion: MoveTo 333 174)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			(
				(or
					(Said 'use,backdrop/keycard,card')
					(Said 'backdrop,backdrop/keycard,card/door,hole')
					(Said 'backdrop,backdrop//keycard,card')
					(Said 'unbolt,open/door')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (ego has: 9)) (Print 360 0))
					(
						(and
							(not (& (ego onControl:) (aLockerDoor doorCtrl?)))
							(not (& (ego onControl:) (aStudioDoor doorCtrl?)))
							(not (& (ego onControl:) $0020))
						)
						(NotClose)
					)
					((& (ego onControl:) $0020) (Print 360 1) (Print 360 2))
					((& (ego onControl:) (aLockerDoor doorCtrl?)) (RoomScript changeState: 1))
					((& (ego onControl:) (aStudioDoor doorCtrl?)) (RoomScript changeState: 7))
				)
			)
			((Said '/club,class,bell,booth,aerobic') (Print 360 3))
			((Said '/bookcase,buffet') (Print 360 4))
			((Said '/blender') (Print 360 5))
			((Said '/blouse') (Print 360 6))
			((Said 'get/soap') (Print 360 7))
			((Said 'look>')
				(cond 
					((Said '/door')
						(cond 
							((& (ego onControl:) (aLockerDoor doorCtrl?)) (Print 360 8))
							((& (ego onControl:) (aStudioDoor doorCtrl?)) (Print 360 9))
							((& (ego onControl:) $0020) (Print 360 10))
							(else (Print 360 11))
						)
					)
					((Said '/awning,board,note,art') (Print 360 12))
					((Said '/man')
						(if (cast contains: aRobin)
							(Print 360 13)
						else
							(Print 360 14)
						)
					)
					((Said '/buffet,buffet<behind,back')
						(if (cast contains: aRobin)
							(Print 360 15)
						else
							(Print 360 16)
							(Print 360 17)
						)
					)
					((Said '/buffet,buffet') (if musicLoop (Print 360 18) else (Print 360 19)))
					((Said '/burn') (Print 360 20))
					((Said '/board,bulletin,note,awning') (Print 360 21))
					((Said '/announcement') (Print 360 22))
					((Said '[/club,club,area]') (Print 360 23))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 24)
						(< (event x?) 289)
						(> (event y?) 182)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 157 192)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 57)
						(< (event x?) 249)
						(> (event y?) 122)
						(< (event y?) 181)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 360 23))
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aRobin (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(if (cast contains: aRobin)
								(Print 360 13)
							else
								(Print 360 14)
							)
						)
						(9
							(cond 
								((not (ego has: 9)) (DontHave) (event claimed: 1))
								((not (& (ego onControl:) $0080)) (NotClose))
								(else
									(Print 360 34 #icon 9 0 0)
									(ManScript changeState: 6 register: 101)
								)
							)
						)
						(996
							(= local1
								(proc255_6
									{What do you want to ask about?}
									81
									{Door}
									1
									81
									{Key card}
									2
									81
									{Locker}
									4
									81
									{Combination}
									5
								)
							)
							(switch local1
								(1
									(cond 
										(musicLoop (Print 360 28))
										((not (& (ego onControl:) $0080)) (NotClose))
										(else (ManScript changeState: 6 register: (++ talkCount)))
									)
								)
								(2
									(Print 360 29)
									(ManScript changeState: 6 register: 102)
								)
								(3
									(Print 360 30)
									(ManScript changeState: 6 register: 104)
								)
								(5
									(Print 360 32)
									(ManScript changeState: 6 register: 108)
								)
								(6
									(Print 360 33)
									(ManScript changeState: 6 register: 107)
								)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aLockerDoor (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((& (ego onControl:) (aLockerDoor doorCtrl?)) (Print 360 8))
								((& (ego onControl:) (aStudioDoor doorCtrl?)) (Print 360 9))
								((& (ego onControl:) $0020) (Print 360 10))
								(else (Print 360 11))
							)
						)
						(9
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (ego has: 9)) (Print 360 0))
								((not (ego has: 8)) (Print 370 55))
								(
									(and
										(not (& (ego onControl:) (aLockerDoor doorCtrl?)))
										(not (& (ego onControl:) (aStudioDoor doorCtrl?)))
										(not (& (ego onControl:) $0020))
									)
									(NotClose)
								)
								((& (ego onControl:) $0020) (Print 360 1) (Print 360 2))
								((& (ego onControl:) (aLockerDoor doorCtrl?)) (RoomScript changeState: 1))
								((& (ego onControl:) (aStudioDoor doorCtrl?)) (RoomScript changeState: 7))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
				(proc0_26 atpRearCardHole (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((& (ego onControl:) (aLockerDoor doorCtrl?)) (Print 360 8))
								((& (ego onControl:) (aStudioDoor doorCtrl?)) (Print 360 9))
								((& (ego onControl:) $0020) (Print 360 10))
								(else (Print 360 11))
							)
						)
						(9
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (ego has: 9)) (Print 360 0))
								(
									(and
										(not (& (ego onControl:) (aLockerDoor doorCtrl?)))
										(not (& (ego onControl:) (aStudioDoor doorCtrl?)))
										(not (& (ego onControl:) $0020))
									)
									(NotClose)
								)
								((& (ego onControl:) $0020) (Print 360 1) (Print 360 2))
								((& (ego onControl:) (aLockerDoor doorCtrl?)) (RoomScript changeState: 1))
								((& (ego onControl:) (aStudioDoor doorCtrl?)) (RoomScript changeState: 7))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
				(proc0_26 atpLeftCardHole (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((& (ego onControl:) (aLockerDoor doorCtrl?)) (Print 360 8))
								((& (ego onControl:) (aStudioDoor doorCtrl?)) (Print 360 9))
								((& (ego onControl:) $0020) (Print 360 10))
								(else (Print 360 11))
							)
						)
						(9
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (ego has: 9)) (Print 360 0))
								((not (ego has: 8)) (Print 370 55))
								((not (ego has: 5)) (Print 360 7))
								(
									(and
										(not (& (ego onControl:) (aLockerDoor doorCtrl?)))
										(not (& (ego onControl:) (aStudioDoor doorCtrl?)))
										(not (& (ego onControl:) $0020))
									)
									(NotClose)
								)
								((& (ego onControl:) $0020) (Print 360 1) (Print 360 2))
								((& (ego onControl:) (aLockerDoor doorCtrl?)) (RoomScript changeState: 1))
								((& (ego onControl:) (aStudioDoor doorCtrl?)) (RoomScript changeState: 7))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
				(proc0_26 atpRightCardHole (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((& (ego onControl:) (aLockerDoor doorCtrl?)) (Print 360 8))
								((& (ego onControl:) (aStudioDoor doorCtrl?)) (Print 360 9))
								((& (ego onControl:) $0020) (Print 360 10))
								(else (Print 360 11))
							)
						)
						(9
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (ego has: 9)) (Print 360 0))
								(
									(and
										(not (& (ego onControl:) (aLockerDoor doorCtrl?)))
										(not (& (ego onControl:) (aStudioDoor doorCtrl?)))
										(not (& (ego onControl:) $0020))
									)
									(NotClose)
								)
								((& (ego onControl:) $0020) (Print 360 1) (Print 360 2))
								((& (ego onControl:) (aLockerDoor doorCtrl?)) (RoomScript changeState: 1))
								((& (ego onControl:) (aStudioDoor doorCtrl?)) (RoomScript changeState: 7))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 atpBboard (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(Print 360 21)
							(Print 360 22)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aRobin of Act
	(properties
		y 105
		x 133
		view 362
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			setPri: 10
			setCycle: Walk
			setScript: ManScript
		)
	)
)

(instance ManScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 4 8)))
			(1
				(aRobin
					illegalBits: 0
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo (Random 101 155) 105 self
				)
			)
			(2 (= seconds (Random 1 3)))
			(4
				(aRobin setLoop: 3)
				(= seconds (Random 4 8))
				(= state -1)
			)
			(6
				(HandsOff)
				(= seconds 0)
				(aRobin setMotion: 0 setLoop: 2 setCycle: Fwd)
				(= seconds 3)
			)
			(7
				(aRobin setCel: 0)
				(switch register
					(1 (Print 360 36))
					(2 (Print 360 37))
					(3 (Print 360 38))
					(4
						(cond 
							((< global98 3) (Print 360 39))
							((== global98 3) (Print 360 40))
							(else (Print 360 41))
						)
					)
					(101 (Print 360 42))
					(102 (Print 360 43))
					(103
						(Print 360 44)
						(Print 360 45)
					)
					(104 (Print 360 46))
					(105 (Print 360 47))
					(106 (Print 360 48))
					(107 (Print 360 49))
					(108 (Print 360 50))
					(else 
						(Print 360 51)
						(= talkCount 4)
					)
				)
				(= register 0)
				(HandsOn)
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said 'address/man')
				(cond 
					(musicLoop (Print 360 28))
					((not (& (ego onControl:) $0080)) (NotClose))
					(else (ManScript changeState: 6 register: (++ talkCount)))
				)
			)
			((Said 'ask>')
				(cond 
					(musicLoop (Print 360 28))
					((not (& (ego onControl:) $0080)) (NotClose))
					((or (Said '/door') (Said '//door')) (Print 360 29) (ManScript changeState: 6 register: 102))
					(
						(or
							(Said '/keycard,camp,key,membership,card')
							(Said 'enroll')
							(Said '//keycard,key,camp,membership,card')
						)
						(Print 360 30)
						(ManScript changeState: 6 register: 104)
					)
					(
					(or (Said '/bambi,tape') (Said '//tape,bambi')) (Print 360 31) (ManScript changeState: 6 register: 106))
					((or (Said '/locker') (Said '//locker')) (Print 360 32) (ManScript changeState: 6 register: 108))
					(
					(or (Said '/combination') (Said '//combination')) (Print 360 33) (ManScript changeState: 6 register: 107))
					(else (ManScript changeState: 6 register: 103))
				)
				(event claimed: 1)
			)
			((Said 'show/keycard')
				(cond 
					((not (ego has: 9)) (DontHave) (event claimed: 1))
					((not (& (ego onControl:) $0080)) (NotClose))
					(else
						(Print 360 34 #icon 9 0 0)
						(ManScript changeState: 6 register: 101)
					)
				)
			)
			((Said 'give')
				(if (& (ego onControl:) $0080)
					(ManScript changeState: 6 register: 105)
				else
					(NotClose)
				)
			)
			((Said '/man') (Print 360 35))
		)
	)
)

(instance aBambi of Act
	(properties
		y 126
		x 200
		view 393
		loop 2
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			setCycle: Walk
		)
	)
)

(instance aTanBoothDoor of Prop
	(properties
		y 171
		x 302
		view 360
		loop 1
		cycleSpeed 2
	)
)

(instance aStudioDoor of Door
	(properties
		y 117
		x 198
		view 360
		loop 2
		entranceTo 390
		locked 1
		doorCtrl 8
		roomCtrl 16
		doorBlock 8192
	)
)

(instance aLockerDoor of Door
	(properties
		y 172
		x 19
		view 360
		entranceTo 370
		locked 1
		roomCtrl 0
	)
)

(instance atpRightCardHole of PV
	(properties
		y 165
		x 314
		view 360
		loop 3
		priority 14
	)
)

(instance atpLeftCardHole of PV
	(properties
		y 143
		x 43
		view 360
		loop 3
		cel 1
		priority 11
	)
)

(instance atpRearCardHole of PV
	(properties
		y 103
		x 224
		view 360
		loop 3
		cel 2
		priority 8
	)
)

(instance atpFatCity of PV
	(properties
		y 40
		x 129
		view 360
		loop 4
		priority 0
	)
)

(instance atpBboard of PV
	(properties
		y 134
		x 257
		view 360
		loop 4
		cel 1
		priority 9
	)
)

(instance atpShelves of PV
	(properties
		y 104
		x 131
		view 360
		loop 4
		cel 2
		priority 6
	)
)

(instance atpSumtin of PV
	(properties
		y 93
		x 170
		view 360
		loop 4
		cel 3
		priority 8
	)
)

(instance atpBlender of PV
	(properties
		y 109
		x 91
		view 360
		loop 4
		cel 4
		priority 9
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include sci.sh)
(use Main)
(use n021)
(use AutoDoor)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm320 0
)
(synonyms
	(man hardy attendant man)
)

(local
	[str 301]
	local301
	local302
)
(instance rm320 of Rm
	(properties
		picture 320
		south 310
	)
	
	(method (init)
		(if (= global94 500) (Load rsVIEW 24))
		(Load rsSOUND 323)
		(super init:)
		(addToPics
			add: atpDeskStuff
			add: atpPencils
			add: atpDCHSign
			add: atpSign
			add: atpFax
			doit:
		)
		(aDoor init: locked: 1)
		(if musicLoop (= lastSysGlobal 8))
		(if (< lastSysGlobal 8)
			(aRoger init:)
			(if (> global87 16) (aFax init:))
		)
		(self setScript: RoomScript)
		(if (or (== prevRoomNum 323) (== prevRoomNum 324))
			(ego loop: 2 posn: 153 96)
		else
			(ego loop: 3 posn: 156 186)
		)
		(if (!= prevRoomNum 323)
			(gTheMusic number: 323 loop: global72 play:)
		)
		(NormalEgo)
		(ego init:)
	)
	
	(method (newRoom newRoomNumber)
		(cond 
			(
			(and (== lastSysGlobal 2) (== (ego edgeHit?) 3)) (= lastSysGlobal 3))
			(
			(and (== lastSysGlobal 6) (== (ego edgeHit?) 3)) (= lastSysGlobal 7))
		)
		(super newRoom: newRoomNumber)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 4)
		(switch (= state newState)
			(0)
			(1)
			(2
				(HandsOff)
				(theGame changeScore: 10)
			)
			(3
				(HandsOff)
				(aDoor locked: 0)
				(ego illegalBits: 0 setMotion: MoveTo 233 (ego y?) self)
			)
			(4
				(ego setMotion: MoveTo 233 130 self)
				(if (== gCurRoomNum 11)
					(Format @str 320 15)
					(SecretaryScript changeState: 2)
				)
			)
			(5
				(ego setMotion: MoveTo 153 93 self)
			)
			(6
				(ego setMotion: MoveTo 153 87 self)
			)
			(7
				(if (== gCurRoomNum 11)
					(curRoom newRoom: 324)
				else
					(curRoom newRoom: 323)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'get/palm') (Print 320 0))
			((Said '/equipment') (Print 320 1) (Print 320 2 #at -1 144))
			((Said '/computer') (Print 320 3))
			((Said '/call') (Print 320 4))
			((Said 'look>')
				(cond 
					((Said '/palm') (Print 320 5))
					((Said '/awning,wall') (Print 320 6))
					((Said '/burn,ceiling') (Print 320 7))
					((Said '/buffet,buffet,buffet') (Print 320 8))
					((Said '/door')
						(cond 
							((& (ego onControl:) $0008) (Print 320 9))
							((& (ego onControl:) $0002) (Print 320 10))
							((& (ego onControl:) $0010) (Print 320 11))
							(else (Print 320 12))
						)
					)
					((Said '[/area]')
						(Print 320 13)
						(if (cast contains: aRoger) (Print 320 14))
					)
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 93)
						(< (event x?) 214)
						(> (event y?) 175)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 146 195)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 9)
						(< (event x?) 301)
						(> (event y?) 21)
						(< (event y?) 163)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(Print 320 13)
							(if (cast contains: aRoger) (Print 320 14))
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 1)
						(< (event x?) 42)
						(> (event y?) 107)
						(< (event y?) 180)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 320 5))
						(995 (Print 320 0))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 277)
						(< (event x?) 313)
						(> (event y?) 117)
						(< (event y?) 180)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 320 5))
						(995 (Print 320 0))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(proc0_26 aRoger (event x?) (event y?))
						(cast contains: aRoger)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 320 16))
						(24
							(cond 
								((not (ego has: 6)) (Print 320 36))
								((not (& (ego onControl:) $0020)) (NotClose))
								((and (!= gCurRoomNum 0) (!= gCurRoomNum 11)) (GoodIdea))
								((!= local301 0) (Print 320 17))
								((== lastSysGlobal 0)
									(User canInput: 0)
									(Format @str 320 37)
									(self changeState: 2)
								)
								((== lastSysGlobal 1)
									(User canInput: 0)
									(Format @str 320 38)
									(self changeState: 2)
								)
								((> lastSysGlobal 4)
									(User canInput: 0)
									(Format @str 320 39)
									(self changeState: 2)
								)
								((!= global94 500) (Print 320 40) (Print 320 41 #at -1 144))
								(else
									(Ok)
									(= lastSysGlobal 5)
									(= global94 0)
									(ego put: 6 -1)
									(User canInput: 0)
									(Print 320 42 #icon 24 0 0)
									(Format @str 320 43)
									(RoomScript changeState: 2)
									(self changeState: 2)
									(= gTheCursor 900)
									(theGame setCursor: 998 (HaveMouse))
								)
							)
						)
						(996
							(= local302
								(Print
									{What do you want to ask about?}
									#button
									{decree}
									1
									#button
									{land}
									2
								)
							)
							(switch local302
								(1
									(cond 
										((not (& (ego onControl:) $0020)) (NotClose))
										((and (!= gCurRoomNum 0) (!= gCurRoomNum 11)) (GoodIdea))
										((!= local301 0) (Print 320 17))
										((== lastSysGlobal 0)
											(User canInput: 0)
											(Printf 320 44 global171)
											(Print 320 45)
											(Format @str 320 46)
											(SecretaryScript changeState: 2)
										)
										((== lastSysGlobal 1)
											(User canInput: 0)
											(Printf 320 44 global171)
											(Print 320 47)
											(Format @str 320 48)
											(RoomScript changeState: 2)
											(SecretaryScript changeState: 2)
										)
										((== lastSysGlobal 2)
											(User canInput: 0)
											(Print 320 49)
											(Format @str 320 50)
											(SecretaryScript changeState: 2)
										)
										((< lastSysGlobal 6)
											(User canInput: 0)
											(Print 320 51)
											(Format @str 320 52)
											(SecretaryScript changeState: 2)
										)
										((< lastSysGlobal 7)
											(User canInput: 0)
											(Print 320 53)
											(Format @str 320 54)
											(SecretaryScript changeState: 2)
										)
										((> lastSysGlobal 7)
											(User canInput: 0)
											(Print 320 55)
											(Format @str 320 56)
											(SecretaryScript changeState: 2)
										)
										(else
											(User canInput: 0)
											(Print 320 57)
											(Format
												@str
												320
												58
												(if (Btst 45)
													{business doing pleasure}
												else
													{pleasure doing business}
												)
											)
											(= lastSysGlobal 8)
											(ego get: 10)
											(theGame changeScore: 20)
											(SecretaryScript changeState: 2)
										)
									)
								)
								(2
									(cond 
										((not (& (ego onControl:) $0020)) (NotClose))
										((and (!= gCurRoomNum 0) (!= gCurRoomNum 11)) (GoodIdea))
										((!= local301 0) (Print 320 17))
										((== lastSysGlobal 0)
											(User canInput: 0)
											(Printf 320 44 global171)
											(Print 320 59)
											(Format @str 320 60)
											(SecretaryScript changeState: 2)
										)
										((== lastSysGlobal 1)
											(User canInput: 0)
											(Printf 320 44 global171)
											(Print 320 61)
											(Format @str 320 62)
											(RoomScript changeState: 2)
											(SecretaryScript changeState: 2)
										)
										((< lastSysGlobal 3)
											(User canInput: 0)
											(Print 320 21)
											(Format @str 320 22)
											(SecretaryScript changeState: 2)
										)
										((> lastSysGlobal 3)
											(User canInput: 0)
											(Print 320 63)
											(Format @str 320 64)
											(SecretaryScript changeState: 2)
										)
										(else
											(User canInput: 0)
											(Print 320 65)
											(Format @str 320 66)
											(= lastSysGlobal 4)
											(ego get: 7)
											(theGame changeScore: 20)
											(SecretaryScript changeState: 2)
										)
									)
								)
								(else  (event claimed: 0))
							)
						)
					)
				)
			)
		)
	)
)

(instance SecretaryScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0
				(aRoger cycleSpeed: 0 loop: 2 setCel: 0 setCycle: 0)
				(= local301 0)
				(= cycles 0)
				(= seconds (Random 3 6))
			)
			(1
				(if (== local301 0) (= local301 (Random 320 327)))
				(cond 
					((!= str 0) (self changeState: 2))
					((== local301 320) (self changeState: 4))
					((== local301 323) (self changeState: 6))
					((== local301 321) (self changeState: 9))
					(else (self changeState: 0))
				)
			)
			(2
				(= local301 322)
				(aRoger loop: 3 setCycle: Fwd)
				(= cycles (Random 11 44))
			)
			(3
				(if (== str 0)
					(switch (Random 1 5)
						(1 (Format @str 320 67))
						(2 (Format @str 320 68))
						(3 (Format @str 320 69))
						(4 (Format @str 320 70))
						(5 (Format @str 320 71))
					)
				)
				(Print
					@str
					#at
					-1
					10
					#title
					{Roger says}
					#mode
					1
					#icon
					321
					5
					0
				)
				(User canInput: 1)
				(if (== (RoomScript state?) 2) (RoomScript cue:))
				(= str 0)
				(= cycles 22)
				(= state -1)
			)
			(4
				(aRoger loop: 2 setCycle: Fwd cycleSpeed: 1)
				(= cycles (Random 9 19))
			)
			(5
				(aRoger setCel: 0)
				(= cycles (Random 9 19))
				(if (Random 0 2) (= state 3) else (= state -1))
			)
			(6
				(aRoger cycleSpeed: 2 loop: 4 cel: 0 setCycle: End self)
			)
			(7 (= cycles (Random 9 19)))
			(8
				(aRoger setCycle: Beg self)
				(= state -1)
			)
			(9
				(aRoger cycleSpeed: 1 loop: 0 cel: 0 setCycle: End self)
			)
			(10
				(aRoger cycleSpeed: 1 loop: 1 setCycle: Fwd)
				(= cycles (Random 9 19))
			)
			(11
				(aRoger setCel: 0)
				(= cycles (Random 9 19))
				(if (Random 0 3) (= state 9))
			)
			(12
				(aRoger loop: 0 setCel: 255 setCycle: Beg self)
				(= state -1)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/man') (Print 320 16))
			(
				(or
					(Said '/appointment')
					(Said '//appointment')
					(Said 'address')
				)
				(cond 
					((not (& (ego onControl:) $0020)) (NotClose))
					((and (!= gCurRoomNum 0) (!= gCurRoomNum 11)) (GoodIdea))
					((!= local301 0) (Print 320 17))
					(else
						(User canInput: 0)
						(switch lastSysGlobal
							(0
								(Print 320 18)
								(Format @str 320 19)
								(self changeState: 2)
							)
							(1
								(Print 320 18)
								(Format @str 320 20)
								(self changeState: 2)
							)
							(2
								(Print 320 21)
								(Format @str 320 22)
								(self changeState: 2)
							)
							(3
								(Print 320 23)
								(Format @str 320 24)
								(self changeState: 2)
							)
							(4
								(Print 320 25)
								(Format @str 320 26)
								(self changeState: 2)
							)
							(6
								(Print 320 27)
								(Format @str 320 28)
								(self changeState: 2)
							)
							(7
								(Print 320 23)
								(Format @str 320 24)
								(self changeState: 2)
							)
							(8
								(Print 320 29)
								(Format @str 320 30)
								(self changeState: 2)
							)
						)
					)
				)
			)
			(
				(and
					(ego has: 9)
					(or
						(Said '/keycard,(card<key,club,membership)')
						(Said '//keycard,(card<key,club,membership)')
					)
				)
				(Print 320 31)
				(Format @str 320 32)
			)
			(
			(or (Said '/entertainer') (Said '//entertainer'))
				(User canInput: 0)
				(Print 320 33)
				(Format @str 320 34)
				(self changeState: 2)
			)
			(
				(or
					(Said '/attorney,attorney,attorney')
					(Said '//attorney,attorney,attorney')
				)
				(User canInput: 0)
				(Format @str 320 35)
				(self changeState: 2)
			)
			(
				(or
					(Said 'affirmative')
					(Said 'give,buy,use/buck,man,charge,bill')
				)
				(cond 
					((not (ego has: 6)) (Print 320 36))
					((not (& (ego onControl:) $0020)) (NotClose))
					((and (!= gCurRoomNum 0) (!= gCurRoomNum 11)) (GoodIdea))
					((!= local301 0) (Print 320 17))
					((== lastSysGlobal 0)
						(User canInput: 0)
						(Format @str 320 37)
						(self changeState: 2)
					)
					((== lastSysGlobal 1)
						(User canInput: 0)
						(Format @str 320 38)
						(self changeState: 2)
					)
					((> lastSysGlobal 4)
						(User canInput: 0)
						(Format @str 320 39)
						(self changeState: 2)
					)
					((!= global94 500) (Print 320 40) (Print 320 41 #at -1 144))
					(else
						(Ok)
						(= lastSysGlobal 5)
						(= global94 0)
						(ego put: 6 -1)
						(User canInput: 0)
						(Print 320 42 #icon 24 0 0)
						(Format @str 320 43)
						(RoomScript changeState: 2)
						(self changeState: 2)
					)
				)
			)
			(
				(and
					(ego has: 10)
					(not (ego has: 9))
					(Said 'look,look/decree,document,document')
				)
				(event claimed: 0)
				(return)
			)
			(
				(or
					(Said '/decree,document,(document<decree)')
					(Said '//decree,document,(document<decree)')
				)
				(cond 
					((not (& (ego onControl:) $0020)) (NotClose))
					((and (!= gCurRoomNum 0) (!= gCurRoomNum 11)) (GoodIdea))
					((!= local301 0) (Print 320 17))
					((== lastSysGlobal 0)
						(User canInput: 0)
						(Printf 320 44 global171)
						(Print 320 45)
						(Format @str 320 46)
						(self changeState: 2)
					)
					((== lastSysGlobal 1)
						(User canInput: 0)
						(Printf 320 44 global171)
						(Print 320 47)
						(Format @str 320 48)
						(RoomScript changeState: 2)
						(self changeState: 2)
					)
					((== lastSysGlobal 2)
						(User canInput: 0)
						(Print 320 49)
						(Format @str 320 50)
						(self changeState: 2)
					)
					((< lastSysGlobal 6)
						(User canInput: 0)
						(Print 320 51)
						(Format @str 320 52)
						(self changeState: 2)
					)
					((< lastSysGlobal 7)
						(User canInput: 0)
						(Print 320 53)
						(Format @str 320 54)
						(self changeState: 2)
					)
					((> lastSysGlobal 7)
						(User canInput: 0)
						(Print 320 55)
						(Format @str 320 56)
						(self changeState: 2)
					)
					(else
						(User canInput: 0)
						(Print 320 57)
						(Format
							@str
							320
							58
							(if (Btst 45)
								{business doing pleasure}
							else
								{pleasure doing business}
							)
						)
						(= lastSysGlobal 8)
						(ego get: 10)
						(theGame changeScore: 20)
						(self changeState: 2)
					)
				)
			)
			((and (ego has: 7) (Said 'look/deed,land')) (event claimed: 0) (return))
			((or (Said '//deed,land') (Said '/deed,land'))
				(cond 
					((not (& (ego onControl:) $0020)) (NotClose))
					((and (!= gCurRoomNum 0) (!= gCurRoomNum 11)) (GoodIdea))
					((!= local301 0) (Print 320 17))
					((== lastSysGlobal 0)
						(User canInput: 0)
						(Printf 320 44 global171)
						(Print 320 59)
						(Format @str 320 60)
						(self changeState: 2)
					)
					((== lastSysGlobal 1)
						(User canInput: 0)
						(Printf 320 44 global171)
						(Print 320 61)
						(Format @str 320 62)
						(RoomScript changeState: 2)
						(self changeState: 2)
					)
					((< lastSysGlobal 3)
						(User canInput: 0)
						(Print 320 21)
						(Format @str 320 22)
						(self changeState: 2)
					)
					((> lastSysGlobal 3)
						(User canInput: 0)
						(Print 320 63)
						(Format @str 320 64)
						(self changeState: 2)
					)
					(else
						(User canInput: 0)
						(Print 320 65)
						(Format @str 320 66)
						(= lastSysGlobal 4)
						(ego get: 7)
						(theGame changeScore: 20)
						(self changeState: 2)
					)
				)
			)
		)
	)
)

(instance atpSign of PV
	(properties
		y 125
		x 151
		view 320
		loop 1
		priority 9
		signal $4000
	)
)

(instance atpDCHSign of PV
	(properties
		y 93
		x 151
		view 320
		priority 9
		signal $4000
	)
)

(instance atpDeskStuff of PV
	(properties
		y 141
		x 165
		view 320
		loop 2
		priority 12
	)
)

(instance atpPencils of PV
	(properties
		y 136
		x 105
		view 320
		loop 2
		cel 1
		priority 12
	)
)

(instance atpFax of PV
	(properties
		y 142
		x 106
		view 320
		loop 4
		priority 12
		signal $4000
	)
)

(instance aFax of Extra
	(properties
		y 133
		x 106
		view 320
		loop 5
	)
	
	(method (init)
		(super init:)
		(self
			cycleType: 1
			pauseCel: -2
			minPause: 99
			maxPause: 999
			setPri: 12
			isExtra: 1
			ignoreActors:
			startExtra:
		)
	)
)

(instance aRoger of Prop
	(properties
		y 130
		x 146
		view 321
		loop 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 12 setScript: SecretaryScript)
	)
)

(instance aDoor of AutoDoor
	(properties
		y 39
		x 153
		view 320
		entranceTo 323
	)
	
	(method (init)
		(super init:)
		(self setLoop: 3 setPri: 5)
	)
)

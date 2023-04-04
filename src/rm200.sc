;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm200 0
)

(local
	local0
	[plotString 222]
)
(procedure (localproc_000c &tmp temp0)
	(Print
		@plotString
		#at
		10
		5
		#width
		290
		#time
		(= temp0 (PrintDelay @plotString))
		#dispose
	)
	(return (+ 3 temp0))
)

(instance rm200 of Rm
	(properties
		picture 200
		east 210
		south 210
	)
	
	(method (init)
		(Load rsVIEW 200)
		(super init:)
		(self setScript: RoomScript)
		(if (and (Btst 17) (not (Btst 24)))
			(Load rsVIEW 201)
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(addToPics add: atpBinocular1 add: atpBinocular2 doit:)
		(if
		(and (!= prevRoomNum 203) (!= prevRoomNum 206))
			(ego posn: 315 167 loop: 1)
		)
		(NormalEgo)
		(ego init:)
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
			(0
				(if (not (Btst 24)) (= seconds 5))
			)
			(1
				(Format @plotString 200 18)
				(= seconds (localproc_000c))
			)
			(2
				(Bset 24)
				(if (not (Btst 20))
					(Format @plotString 200 19)
					(= seconds (localproc_000c))
				)
			)
			(3 (= seconds 0))
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				modelessDialog
				(== (event message?) KEY_RETURN)
				(== (event type?) evKEYBOARD)
			)
			(event claimed: 1)
			(cls)
			(self cue:)
		)
		(cond 
			((Said '/binocular,binocular>')
				(cond 
					((Said 'get') (Print 200 0))
					((Said 'use,(look<through,in)')
						(cond 
							(musicLoop (Print 200 1))
							(
								(and
									(not (& (ego onControl:) $0004))
									(not (& (ego onControl:) $0008))
								)
								(NotClose)
							)
							((& (ego onControl:) $0008) (Print 200 2))
							((Btst 16) (Print 200 3))
							(else (Ok) (curRoom newRoom: 206))
						)
					)
					((Said 'look') (Print 200 4))
				)
			)
			((Said 'get/awning') (Print 200 5) (Print 200 6 #at -1 144))
			((or (Said 'look/air') (Said 'up<look')) (Print 200 7))
			((Said 'look>')
				(cond 
					((Said '/air,up') (Print 200 7))
					((Said '/cliff,land,cliff') (Print 200 8))
					((Said '/fence,rail') (Print 200 9))
					((Said '/cliff,edge') (Print 200 10))
					((Said '/bay,beach,point,bay,bay') (Print 200 11))
					((Said '/camp,down,building,casino,hotel,trap') (Print 200 12) (if (<= global88 1) (Print 200 13)))
					((Said '/blade,carpet,carpet') (Print 200 14))
					((Said '/bird') (Print 200 15))
					((Said '/awning')
						(if (& (ego onControl:) $0002)
							(if (not (Btst 18))
								(Bset 18)
								(theGame changeScore: 2)
							)
							(Ok)
							(curRoom newRoom: 203)
						else
							(NotClose)
						)
					)
					((Said '[/look,area]') (Print 200 16) (Print 200 17))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 310)
						(< (event x?) 319)
						(> (event y?) 146)
						(< (event y?) 169)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 320 160)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 0)
						(< (event x?) 318)
						(> (event y?) 28)
						(< (event y?) 53)
					)
					(event claimed: 1)
					(switch theCursor
						(994 (Print 200 7))
						(998 (Print 200 8))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 0)
						(< (event x?) 75)
						(> (event y?) 84)
						(< (event y?) 186)
					)
					(event claimed: 1)
					(switch theCursor
						(994 (Print 200 7))
						(998
							(Print 200 16)
							(Print 200 17)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 159)
						(< (event x?) 170)
						(> (event y?) 92)
						(< (event y?) 110)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(if (& (ego onControl:) $0002)
								(if (not (Btst 18))
									(Bset 18)
									(theGame changeScore: 2)
								)
								(Ok)
								(curRoom newRoom: 203)
							else
								(NotClose)
								(ego setMotion: MoveTo 163 112)
							)
						)
						(995
							(Print 200 5)
							(Print 200 6 #at -1 144)
						)
						(999
							(ego setMotion: MoveTo 163 112)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(or
						(proc0_26 atpBinocular1 (event x?) (event y?))
						(proc0_26 atpBinocular2 (event x?) (event y?))
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								(musicLoop (Print 200 1))
								(
									(and
										(not (& (ego onControl:) $0004))
										(not (& (ego onControl:) $0008))
									)
									(NotClose)
								)
								((Btst 16) (Print 200 3))
								(else (Ok) (curRoom newRoom: 206))
							)
						)
						(995 (Print 200 0))
						(else  (event claimed: 0))
					)
					(cls)
				)
			)
		)
	)
)

(instance atpBinocular1 of PV
	(properties
		y 82
		x 137
		view 200
		signal $4000
	)
)

(instance atpBinocular2 of PV
	(properties
		y 79
		x 205
		view 200
		loop 6
		cel 1
		signal $4000
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 240
		view 201
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors:)
	)
)

(instance aCredit2 of Prop
	(properties
		y 154
		x 240
		view 201
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors: setScript: CreditsScript)
	)
)

(instance CreditsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(aCredit1 setCycle: End)
				(= cycles 16)
			)
			(2
				(aCredit2 setCycle: End)
				(= cycles 22)
			)
			(3
				(Bset 24)
				(aCredit1 setCycle: Beg)
				(aCredit2 setCycle: Beg self)
			)
			(4
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)

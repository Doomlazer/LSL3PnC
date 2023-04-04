;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include sci.sh)
(use Main)
(use AutoDoor)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm310 0
)

(local
	[local0 312]
)
(instance rm310 of Rm
	(properties
		picture 310
		south 220
	)
	
	(method (init)
		(super init:)
		(addToPics add: atpSign doit:)
		(if (< lastSysGlobal 8)
			(addToPics add: atpRoger doit:)
		else
			(addToPics add: atpNoRoger doit:)
		)
		(aDoor init:)
		(self setScript: RoomScript)
		(if (== prevRoomNum 320)
			(ego posn: 220 88)
		else
			(ego posn: 84 188)
		)
		(NormalEgo)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'look<down') (Print 310 0))
			(
				(and
					(ego has: 2)
					(!= ((inventory at: 2) view?) 21)
					(Said 'sharpen/ginsu')
				)
				(Print 310 1)
			)
			((Said 'look>')
				(cond 
					((Said '/camp,beach,bay,water') (Print 310 2))
					((Said '/awning') (Print 310 3))
					((Said '/bush') (Print 310 4))
					((Said '/flower,boulder,wall') (Print 310 5))
					((Said '[/area]') (Print 310 6) (Print 310 7 #at -1 144))
				)
			)
			((Said 'get/bush') (Print 310 8))
			((Said 'get,pick/flower') (Print 310 9))
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 37)
						(< (event x?) 129)
						(> (event y?) 171)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 75 190)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 1)
						(< (event x?) 44)
						(> (event y?) 41)
						(< (event y?) 132)
					)
					(event claimed: 1)
					(switch theCursor
						(2 (Print 310 1))
						(995 (Print 310 9))
						(998 (Print 310 5))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 68)
						(< (event x?) 316)
						(> (event y?) 38)
						(< (event y?) 169)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(Print 310 6)
							(Print 310 7 #at -1 144)
						)
						(else  (event claimed: 0))
					)
				)
			)
			(else 0)
		)
	)
)

(instance atpSign of PV
	(properties
		y 189
		x 192
		view 310
		priority 14
	)
)

(instance aDoor of AutoDoor
	(properties
		y 87
		x 220
		view 310
		loop 1
		cycleSpeed 2
		entranceTo 320
	)
	
	(method (init)
		(super init:)
		(self setPri: 4)
	)
)

(instance atpRoger of PV
	(properties
		y 45
		x 219
		view 310
		loop 2
		priority 2
	)
)

(instance atpNoRoger of PV
	(properties
		y 51
		x 219
		view 310
		loop 2
		cel 1
		priority 2
	)
)

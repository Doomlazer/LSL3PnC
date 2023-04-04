;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm400 0
)

(instance rm400 of Rm
	(properties
		picture 400
		horizon 83
		north 410
		east 460
		south 250
		west 250
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(switch prevRoomNum
			(460 (ego posn: 318 162))
			(410
				(if (< (ego x?) 111) (ego x: 111))
				(if (> (ego x?) 210) (ego x: 210))
			)
			(else  (ego posn: 159 188))
		)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (Said 'look>')
			(cond 
				((Said '/burn') (Printf 400 0 global82))
				((Said '[/area]') (Print 400 1))
			)
		)
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if
				(and
					(> (event x?) 10)
					(< (event x?) 292)
					(> (event y?) 180)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 143 192)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 312)
					(< (event x?) 319)
					(> (event y?) 136)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 321 159)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 31)
					(< (event x?) 272)
					(> (event y?) 21)
					(< (event y?) 179)
				)
				(event claimed: 1)
				(switch theCursor
					(998 (Print 400 1))
					(else  (event claimed: 0))
				)
			)
		)
	)
)

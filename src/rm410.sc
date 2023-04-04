;;; Sierra Script 1.0 - (do not remove this comment)
(script# 410)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm410 0
)

(local
	local0
)
(instance rm410 of Rm
	(properties
		picture 410
		south 400
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(cond 
			((== prevRoomNum 415) (ego posn: 59 45))
			((== prevRoomNum 416) (ego posn: 263 45))
			(else (ego posn: 159 188))
		)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((& (ego onControl: 1) $0002) (curRoom newRoom: 415))
			((& (ego onControl: 1) $0004) (curRoom newRoom: 416))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (Said 'look>')
			(cond 
				((Said '/art,body,naked,babe,art') (Print 410 0))
				((Said '/column') (Print 410 1))
				((Said '/rail') (Print 410 2))
				((Said '/stair') (Print 410 3))
				((Said '/curtain') (Print 410 4))
				((Said '[/area]') (Print 410 5))
			)
		)
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if
				(and
					(> (event x?) 1)
					(< (event x?) 310)
					(> (event y?) 168)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 160 195)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 1)
					(< (event x?) 319)
					(> (event y?) 21)
					(< (event y?) 169)
				)
				(event claimed: 1)
				(switch theCursor
					(998
						(switch (Random 0 5)
							(0 (Print 410 0))
							(1 (Print 410 1))
							(2 (Print 410 2))
							(3 (Print 410 3))
							(4 (Print 410 4))
							(5 (Print 410 5))
						)
					)
					(else  (event claimed: 0))
				)
			)
		)
	)
)

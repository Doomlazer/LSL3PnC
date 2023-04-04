;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include sci.sh)
(use Main)
(use AutoDoor)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm240 0
)

(local
	noEntryMsg
)
(instance rm240 of Rm
	(properties
		picture 240
		horizon 66
		north 245
		east 245
		west 230
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(aDoor
			locked: (if (!= gCurRoomNum 0) else musicLoop)
			init:
		)
		(cond 
			((== prevRoomNum 230) (ego x: 1))
			((== prevRoomNum 340) (ego posn: 53 77 loop: 2))
			(else (ego posn: 292 73))
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
		(if
			(and
				(& (ego onControl:) $0002)
				(or musicLoop (!= gCurRoomNum 0))
			)
			(if (not noEntryMsg)
				(= noEntryMsg 1)
				(ego
					posn: (ego xLast?) (ego yLast?)
					setMotion: 0
					observeControl: 2
				)
				(if musicLoop (Print 240 0) else (Print 240 1))
				(Animate (cast elements?) 0)
			)
		else
			(= noEntryMsg 0)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (Said 'look>')
			(cond 
				((Said '/club,comedy,building') (Print 240 2))
				((Said '/door') (if musicLoop (Print 240 3) else (Print 240 4)))
				((Said '[/area]')
					(Print 240 5)
					(if musicLoop (Print 240 6) else (Print 240 7))
				)
			)
		)
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if
				(and
					(> (event x?) 15)
					(< (event x?) 257)
					(> (event y?) 84)
					(< (event y?) 175)
				)
				(event claimed: 1)
				(switch theCursor
					(998
						(Print 240 5)
						(if musicLoop (Print 240 6) else (Print 240 7))
					)
					(else  (event claimed: 0))
				)
			)
			(if (proc0_26 aDoor (event x?) (event y?))
				(event claimed: 1)
				(switch theCursor
					(998
						(if musicLoop (Print 240 3) else (Print 240 4))
					)
					(995
						(ego setMotion: MoveTo 50 50)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 1)
					(< (event x?) 11)
					(> (event y?) 97)
					(< (event y?) 129)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -5 120)
					)
				)
			)
		)
	)
)

(instance aDoor of AutoDoor
	(properties
		y 66
		x 53
		view 240
		entranceTo 340
	)
)

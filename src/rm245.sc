;;; Sierra Script 1.0 - (do not remove this comment)
(script# 245)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm245 0
)

(local
	noEntryMsg
)
(instance rm245 of Rm
	(properties
		picture 245
		west 240
	)
	
	(method (init)
		(super init:)
		(self setRegions: 41 setScript: RoomScript)
		(if (== prevRoomNum 500)
			(ego posn: 126 73 loop: 2)
		else
			(ego posn: 5 172 loop: 0)
		)
		(NormalEgo)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
		(if gCurRoomNum (ego observeControl: 4 8 16))
	)
	
	(method (newRoom newRoomNumber)
		(cls)
		(super newRoom: newRoomNumber)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(if (not musicLoop)
			(ego observeControl: 16384)
			(if (& (ego onControl:) $0020)
				(if (not noEntryMsg) (= noEntryMsg 1) (Print 245 0))
			else
				(= noEntryMsg 0)
			)
		)
		(if (== gCurRoomNum 0)
			(cond 
				((& (ego onControl:) $0002)
					(if (not (ego has: 13))
						(Print {Do you have water for the road?})
					)
					(if (not (ego has: 15))
						(Print {You're going to need all your clothes.})
					)
					(if (not (ego has: 16))
						(Print {You forgot to put on your bra.})
					)
					(if (not (ego has: 16))
						(Print {You will need some of magic...})
					)
					(curRoom newRoom: 500)
				)
				((& (ego onControl:) $0010) (NotifyScript 41 9 300))
				((& (ego onControl:) $0004) (NotifyScript 41 0 300))
				(
				(or (== 2 (ego edgeHit?)) (& (ego onControl:) $0008)) (ego x: (+ (ego x?) 8)) (NotifyScript 41 8 300))
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (Said 'look>')
			(cond 
				((Said '/ball,boulder,boob') (Print 245 1))
				((Said '/bamboo') (Print 245 2))
				((Said '[/area]') (Print 245 3))
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
					(< (event x?) 16)
					(> (event y?) 157)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -2 177)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 128)
					(< (event x?) 207)
					(> (event y?) 21)
					(< (event y?) 65)
				)
				(event claimed: 1)
				(switch theCursor
					(998 (Print 245 2))
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 10)
					(< (event x?) 319)
					(> (event y?) 69)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(998 (Print 245 3))
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 208)
					(< (event x?) 256)
					(> (event y?) 28)
					(< (event y?) 69)
				)
				(event claimed: 1)
				(switch theCursor
					(998 (Print 245 1))
					(else  (event claimed: 0))
				)
			)
		)
	)
)

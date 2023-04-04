;;; Sierra Script 1.0 - (do not remove this comment)
(script# 523)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm523 0
)

(instance rm523 of Rm
	(properties
		picture 523
		west 520
	)
	
	(method (init)
		(super init:)
		(self setRegions: 41 setScript: RoomScript)
		(NormalEgo)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(if
		(and (& (ego onControl:) $0002) (== gCurRoomNum 0))
			(NotifyScript 41 2 188)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'use,attach/bra')
				(cond 
					((not (ego has: 16)) (DontHave))
					((!= gCurRoomNum 0) (GoodIdea))
					(else (Print 523 0))
				)
			)
			((Said 'use,attach/hose')
				(if (not (ego has: 15))
					(Print 523 1)
				else
					(Print 523 2)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/boulder') (Print 523 3))
					((Said '[/area]') (Print 523 4))
				)
			)
			((Said '(up<climb),climb/boulder,arch') (Print 523 5))
			(
				(or
					(Said '(climb,go)<(down,above)')
					(Said 'decrease/i')
				)
				(Print 523 6)
			)
			((Said 'climb') (Print 523 7) (Print 523 8 #at -1 144))
			((Said 'drag,grasp,get/bush,hemp') (Print 523 9))
			((Said 'get,use/palm') (Print 523 10))
			((Said '/bush') (Print 523 11))
			((Said '/arch') (Print 523 12))
			((Said '/flower') (Print 523 13))
			((Said 'jump') (Print 523 14))
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 0)
						(< (event x?) 8)
						(> (event y?) 103)
						(< (event y?) 184)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo -4 160)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 1)
						(< (event x?) 319)
						(> (event y?) 21)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(switch (Random 1 2)
								(1 (Print 523 4))
								(2 (Print 523 3))
							)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

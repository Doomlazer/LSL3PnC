;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm300 0
)

(local
	noEntryMsg
	[plotString 301]
)
(instance rm300 of Rm
	(properties
		picture 300
		east 220
	)
	
	(method (init)
		(super init:)
		(addToPics add: atpSign doit:)
		(aSpout init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(if (== prevRoomNum 360)
			(ego posn: 155 190 setPri: 4 setLoop: 2)
			(RoomScript changeState: 1)
			(if
			(and currentEgo (not (Btst 10)) (not (Btst 8)))
				(Bset 53)
			)
		else
			(ego posn: 318 184 loop: 1)
		)
		(ego init:)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $4000)
			(cond 
				((== gCurRoomNum 0)
					(if (not (ego has: 8)) (Print 370 55) else 0)
					(if (not (ego has: 5)) (Print 360 7) else 0)
					(= gCurRoomNum 300)
					(self changeState: 4)
				)
				((== gCurRoomNum 301))
				((not noEntryMsg) (= noEntryMsg 1) (Print 300 0))
			)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(= gCurRoomNum 301)
				(ego illegalBits: 0 setMotion: MoveTo 155 160 self)
			)
			(2
				(ego setMotion: MoveTo 155 164 self)
			)
			(3
				(= gCurRoomNum 0)
				(NormalEgo)
			)
			(4
				(HandsOff)
				(ego
					illegalBits: 0
					setPri: 4
					setLoop: 3
					setMotion: MoveTo (ego x?) 200 self
				)
			)
			(5
				(= gCurRoomNum 0)
				(curRoom newRoom: 360)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (Said 'look>')
			(cond 
				((Said '/fish') (Print 300 1))
				((Said '/awning') (Print 300 2))
				((Said '/panties') (Print 300 3))
				((Said '/eye') (Print 300 4))
				((Said '/lip') (Print 300 5))
				((Said '/dicklicker') (Print 300 6))
				((Said '/spout') (Print 300 7))
				((Said '[/area]') (Print 300 8))
			)
		)
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if
				(and
					(> (event x?) 300)
					(< (event x?) 319)
					(> (event y?) 169)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 322 179)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 89)
					(< (event x?) 260)
					(> (event y?) 50)
					(< (event y?) 135)
				)
				(event claimed: 1)
				(switch theCursor
					(999)
					(998
						(switch (Random 1 5)
							(1 (Print 300 1))
							(2 (Print 300 2))
							(3 (Print 300 3))
							(4 (Print 300 5))
							(5 (Print 300 8))
						)
					)
					(else  (event claimed: 0))
				)
			)
		)
	)
)

(instance atpSign of PV
	(properties
		y 133
		x 62
		view 300
		priority 7
		signal $4000
	)
)

(instance aSpout of Prop
	(properties
		y 56
		x 157
		view 300
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Fwd)
	)
)

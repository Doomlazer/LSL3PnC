;;; Sierra Script 1.0 - (do not remove this comment)
(script# 235)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use System)

(public
	rm235 0
)

(instance rm235 of Rm
	(properties
		picture 235
		west 230
	)
	
	(method (init)
		(if (InRoom 11) (Load rsVIEW 236))
		(super init:)
		(self setRegions: 41 setScript: RoomScript)
		(ego posn: 43 124 loop: 0 init:)
		(NormalEgo)
		(User canInput: 0 mapKeyToDir: 0)
		(if gCurRoomNum (ego observeControl: 2))
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(if
		(and (& (ego onControl:) $0002) (== gCurRoomNum 0))
			(NotifyScript 41 2 300)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					setMotion: MoveTo (if (& (ego onControl:) $0008) 99 else 235) 96 self
				)
			)
			(2
				(ego
					get: 11
					view: 236
					loop: (if (< (ego x?) 160) 1 else 0)
					cel: 0
					cycleSpeed: 1
					setCycle: Fwd
				)
				(= cycles 44)
			)
			(3
				(cond 
					((and (== global74 1) (== global73 0)) (Print 235 12))
					((Btst 54) (Print 235 13))
					(else (Bset 54) (theGame changeScore: 25) (Print 235 14))
				)
				(NormalEgo)
				(SetOrchidTimer 1 10 0)
				((Inv at: 11) view: 11)
				(Format ((Inv at: 11) name?) {Orquids})
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'look<up') (Print 235 0))
			((Said 'look<down') (Print 235 1) (Print 235 2 #at -1 144))
			((Said 'look>')
				(cond 
					((Said '[/area]') (Print 235 3))
					((Said '/carpet,carpet') (Print 235 4))
					((Said '/ceiling,air') (Print 235 0))
					((Said '/camp,beach,bay,water') (Print 235 5) (Print 235 2 #at -1 144))
					((Said '/bush') (Print 235 6))
					((Said '/flower,boulder,wall') (Print 235 7))
				)
			)
			((Said 'get/bush') (Print 235 8))
			((Said 'get,pick/flower')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					(musicLoop (Print 235 9))
					(
						(and
							(not (& (ego onControl:) $0004))
							(not (& (ego onControl:) $0008))
						)
						(Print 235 10)
					)
					((and (== global74 1) (== global73 0)) (self changeState: 1))
					((not (InRoom 11)) (Print 235 11))
					(else (self changeState: 1))
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
					(> (event x?) 35)
					(< (event x?) 61)
					(> (event y?) 80)
					(< (event y?) 137)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 45 125 self)
						(ego setMotion: MoveTo -3 125)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 76)
					(< (event x?) 100)
					(> (event y?) 26)
					(< (event y?) 90)
				)
				(event claimed: 1)
				(switch theCursor
					(995
						(cond 
							((!= gCurRoomNum 0) (GoodIdea))
							(musicLoop (Print 235 9))
							(
								(and
									(not (& (ego onControl:) $0004))
									(not (& (ego onControl:) $0008))
								)
								(Print 235 10)
							)
							((and (== global74 1) (== global73 0)) (self changeState: 1))
							((not (InRoom 11)) (Print 235 11))
							(else (self changeState: 1))
						)
					)
					(998 (Print 235 7))
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 215)
					(< (event x?) 297)
					(> (event y?) 26)
					(< (event y?) 125)
				)
				(event claimed: 1)
				(switch theCursor
					(995
						(cond 
							((!= gCurRoomNum 0) (GoodIdea))
							(musicLoop (Print 235 9))
							(
								(and
									(not (& (ego onControl:) $0004))
									(not (& (ego onControl:) $0008))
								)
								(Print 235 10)
							)
							((and (== global74 1) (== global73 0)) (self changeState: 1))
							((not (InRoom 11)) (Print 235 11))
							(else (self changeState: 1))
						)
					)
					(998 (Print 235 3))
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 110)
					(< (event x?) 205)
					(> (event y?) 20)
					(< (event y?) 188)
				)
				(event claimed: 1)
				(switch theCursor
					(998
						(Print 235 5)
						(Print 235 2 #at -1 144)
					)
					(else  (event claimed: 0))
				)
			)
		)
	)
)

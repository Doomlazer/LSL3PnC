;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm210 0
)

(local
	[plotString 200]
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

(instance rm210 of Rm
	(properties
		picture 210
		south 220
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(cond 
			((not (Btst 25)) (Load rsVIEW 53))
			((not (Btst 20)) (Load rsVIEW 53))
			((not (Btst 25)) (Load rsVIEW 212) (aCredit1 init:) (aCredit2 init:))
		)
		(if (InRoom 3) (Load rsVIEW 709) (aWood init:))
		(cond 
			((== prevRoomNum 200) (ego posn: 317 126 loop: 1))
			((== prevRoomNum 216) (ego posn: 2 163))
			((== prevRoomNum 213) (ego posn: 2 175))
			(else (ego posn: 317 175))
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
		(if (ego edgeHit?)
			(cond 
				((& (ego onControl:) $0008) (curRoom newRoom: 200))
				((& (ego onControl:) $0004) (curRoom newRoom: 216))
				((& (ego onControl:) $0010) (curRoom newRoom: 220))
				((& (ego onControl:) $0002) (curRoom newRoom: 213))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not (Btst 25)) (= cycles 30))
					((not (Btst 20)) (= cycles 20) (++ state))
				)
			)
			(1
				(Format @plotString 210 5)
				(= seconds (localproc_000c))
			)
			(2
				(Bset 25)
				(if (not (Btst 20))
					(aCredit1
						view: 53
						posn: 0 121
						setCycle: Fwd
						cycleSpeed: 1
						ignoreActors:
						init:
						setPri: 12
					)
					(Format @plotString 210 6)
					(= seconds (localproc_000c))
				)
			)
			(3
				(aCredit1 dispose:)
				(= seconds 0)
			)
			(4
				(= cycles (= seconds 0))
				(Ok)
				(HandsOff)
				(if (< (ego x?) (aWood x?))
					(ego
						illegalBits: 0
						setMotion: MoveTo (- (aWood x?) 19) (ego y?) self
					)
				else
					(ego
						illegalBits: 0
						setMotion: MoveTo (+ (aWood x?) 19) (ego y?) self
					)
				)
			)
			(5
				(ego
					view: 709
					loop: (> (ego x?) (aWood x?))
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(6
				(aWood hide:)
				(ego get: 3 setCycle: Beg self)
			)
			(7
				(NormalEgo)
				(theGame changeScore: 2)
				(Print 210 7)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(== (event message?) KEY_RETURN)
				(== (event type?) evKEYBOARD)
			)
			(event claimed: 1)
			(cls)
			(self cue:)
		)
		(if (event claimed?) (return))
		(if (Said 'look>')
			(if (Said '/palm')
				(Printf
					210
					0
					(if (InRoom 3)
						{ Beneath its outstretched boughs lies a beautiful piece of wood, probably cut by a native then forgotten.}
					else
						{}
					)
				)
			)
			(if (and (InRoom 3) (Said '/backdrop,granadilla'))
				(Print 210 1)
			)
			(if (Said '[/area]') (Print 210 2))
		)
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if
				(and
					(> (event x?) 0)
					(< (event x?) 20)
					(> (event y?) 145)
					(< (event y?) 165)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -5 158)
					)
					(998)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 0)
					(< (event x?) 15)
					(> (event y?) 168)
					(< (event y?) 185)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -5 177)
					)
					(998)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 15)
					(< (event x?) 305)
					(> (event y?) 21)
					(< (event y?) 125)
				)
				(event claimed: 1)
				(switch theCursor
					(998
						(switch (Random 1 2)
							(1
								(Printf
									210
									0
									(if (InRoom 3)
										{ Beneath its outstretched boughs lies a beautiful piece of wood, probably cut by a native then forgotten.}
									else
										{}
									)
								)
							)
							(2 (Print 210 2))
						)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 290)
					(< (event x?) 319)
					(> (event y?) 83)
					(< (event y?) 144)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 335 123)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 300)
					(< (event x?) 319)
					(> (event y?) 168)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 335 174)
					)
					(else  (event claimed: 0))
				)
			)
			(if (proc0_26 aWood (event x?) (event y?))
				(event claimed: 1)
				(switch theCursor
					(995
						(cond 
							((!= gCurRoomNum 0) (GoodIdea))
							((not (InRoom 3)) (Print 210 3))
							((not (& (ego onControl:) $0040)) (Print 210 4))
							(else (self changeState: 4))
						)
					)
					(998
						(if (InRoom 3) (Print 210 1))
					)
				)
			)
		)
	)
)

(instance aWood of View
	(properties
		y 161
		x 223
		view 210
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 240
		view 212
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors:)
	)
)

(instance aCredit2 of Prop
	(properties
		y 131
		x 240
		view 212
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
				(Bset 25)
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

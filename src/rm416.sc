;;; Sierra Script 1.0 - (do not remove this comment)
(script# 416)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm416 0
)

(instance rm416 of Rm
	(properties
		picture 416
		east 450
		west 410
	)
	
	(method (init)
		(Load rsVIEW 415)
		(Load rsVIEW 416)
		(Load rsVIEW 417)
		(Load rsVIEW 418)
		(Load rsVIEW 419)
		(super init:)
		(self setRegions: 417)
		(cond 
			((== prevRoomNum 410) (ego posn: 1 143))
			((or (== gCurRoomNum 11) (> (ego yLast?) 180)) (ego posn: 315 136))
			(else (ego posn: 290 122))
		)
		(NormalEgo)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
		(aCraps init: isExtra: 1)
		(aCard init: isExtra: 1)
		(if (> global87 16) (aWalker init:) (aAlterEgo init:))
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(& (ego onControl:) $0002)
				(and (== gCurRoomNum 11) (& (ego onControl:) $0004))
			)
			(curRoom newRoom: 450)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if
				(and
					(> (event x?) 53)
					(< (event x?) 245)
					(> (event y?) 21)
					(< (event y?) 113)
				)
				(event claimed: 1)
				(switch theCursor
					(998
						(switch (Random 0 1)
							(1
								(Print 417 8)
								(if (not (Btst 12))
									(Bset 12)
									(theGame changeScore: 2)
								)
							)
							(0 (Print 417 9))
						)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 260)
					(< (event x?) 288)
					(> (event y?) 78)
					(< (event y?) 128)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 275 75)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 1)
					(< (event x?) 8)
					(> (event y?) 123)
					(< (event y?) 157)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -4 145)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 308)
					(< (event x?) 319)
					(> (event y?) 117)
					(< (event y?) 133)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 322 134)
					)
					(else  (event claimed: 0))
				)
			)
		)
	)
)

(instance aAlterEgo of Act
	(properties
		view 700
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			view: (ego view?)
			setPri: 4
			posn: (ego x?) 122
			ignoreHorizon:
			ignoreActors:
		)
	)
	
	(method (doit)
		(aAlterEgo
			view: (ego view?)
			loop:
			(switch (ego loop?)
				(3 2)
				(2 3)
				(else  (ego loop?))
			)
			cel: (ego cel?)
			x: (ego x?)
			y: (- 125 (/ (- (ego y?) 127) 2))
		)
		(super doit:)
	)
)

(instance aCraps of Extra
	(properties
		y 60
		x 122
		view 414
		loop 3
		cel 11
		cycleSpeed 0
		cycleType 1
		hesitation 11
		pauseCel 11
		minPause 22
		maxPause 111
	)
)

(instance aCard of Extra
	(properties
		y 60
		x 160
		view 414
		loop 1
		minPause 11
		maxPause 55
		minCycles 15
		maxCycles 44
	)
)

(instance aWalker of Act
	(properties
		y 15
		x 119
		view 415
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setCycle: Walk
			setStep: 2 1
			setScript: WalkerScript
		)
	)
)

(instance WalkerScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0 (= seconds (Random 2 6)))
			(1
				(switch (Random 0 6)
					(0
						(aWalker
							view: (Random 415 419)
							posn: 72 15
							setPri: 1
							setMotion: MoveTo 198 15 self
						)
					)
					(1
						(aWalker
							view: (Random 415 419)
							posn: 198 15
							setPri: 1
							setMotion: MoveTo 72 15 self
						)
					)
					(2
						(aWalker
							view: 415
							posn: 132 32
							setPri: 0
							setMotion: MoveTo 132 142 self
						)
					)
					(3
						(aWalker
							view: 415
							posn: 132 142
							setPri: 0
							setMotion: MoveTo 132 32 self
						)
					)
					(else  (= seconds 2))
				)
				(= state -1)
			)
		)
	)
)

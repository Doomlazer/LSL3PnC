;;; Sierra Script 1.0 - (do not remove this comment)
(script# 415)
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
	rm415 0
)

(instance rm415 of Rm
	(properties
		picture 415
		east 410
		west 420
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
			((== prevRoomNum 410) (ego posn: 318 143))
			((or (== gCurRoomNum 11) (> (ego y?) 180)) (ego posn: 1 143))
			(else (ego posn: 31 122))
		)
		(NormalEgo)
		(ego init:)
		(aCraps init: isExtra: 1)
		(aCard init: isExtra: 1)
		(if (> global87 16) (aWalker init:) (aAlterEgo init:))
		(User canInput: 0 mapKeyToDir: 0)
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(& (ego onControl:) $0002)
				(and (== gCurRoomNum 11) (& (ego onControl:) $0004))
			)
			(curRoom newRoom: 420)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if (event claimed: 1)
				(switch theCursor
					(998
						(if (proc0_27 24 300 21 190 event) (Print 499 6))
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 5)
					(< (event x?) 24)
					(> (event y?) 112)
					(< (event y?) 142)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -3 125)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 300)
					(< (event x?) 319)
					(> (event y?) 124)
					(< (event y?) 166)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 322 147)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 74)
					(< (event x?) 270)
					(> (event y?) 21)
					(< (event y?) 115)
				)
				(event claimed: 1)
				(switch theCursor
					(998
						(Print 417 8)
						(if (not (Btst 12))
							(Bset 12)
							(theGame changeScore: 2)
						)
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
		y 62
		x 198
		view 414
		loop 2
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
		y 59
		x 160
		view 414
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
		view 414
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
							posn: 251 15
							setPri: 1
							setMotion: MoveTo 119 15 self
						)
					)
					(1
						(aWalker
							view: (Random 415 419)
							posn: 119 15
							setPri: 1
							setMotion: MoveTo 251 15 self
						)
					)
					(2
						(aWalker
							view: 415
							posn: 186 33
							setPri: 0
							setMotion: MoveTo 186 142 self
						)
					)
					(3
						(aWalker
							view: 415
							posn: 186 142
							setPri: 0
							setMotion: MoveTo 186 33 self
						)
					)
					(else  (= seconds 2))
				)
				(= state -1)
			)
		)
	)
)

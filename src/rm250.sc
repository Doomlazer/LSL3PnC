;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm250 0
)

(local
	nearSteps
	cantGoThere
	nearCasino
	nearOffice
	sharpenCycles
	[plot 200]
)
(procedure (localproc_000c &tmp temp0)
	(Print
		@plot
		#at
		10
		5
		#width
		290
		#time
		(= temp0 (PrintDelay @plot))
		#dispose
	)
	(return (+ 3 temp0))
)

(instance rm250 of Rm
	(properties
		picture 250
		south 260
	)
	
	(method (init)
		(if
		(and (ego has: 2) (== ((Inv at: 2) view?) 2))
			(Load rsVIEW 251)
			(Load rsVIEW 709)
			(Load rsVIEW 2)
			(Load rsSOUND 250)
		)
		(if (== gCurRoomNum 4) (self style: 7))
		(super init:)
		(User canInput: 0 mapKeyToDir: 0)
		(self setScript: RoomScript)
		(if (not (Btst 22)) (Load rsVIEW 53))
		(if (and (Btst 22) (not (Btst 29)))
			(Load rsVIEW 252)
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(if (> global87 16) (aFountain init:))
		(cond 
			((== prevRoomNum 305) (ego posn: 2 186) (= nearOffice 1))
			((== prevRoomNum 220) (ego posn: 2 122))
			((== prevRoomNum 253) (ego posn: 317 125))
			((== prevRoomNum 260) (ego posn: 154 187 loop: 3))
			(else (= nearSteps 1) (ego posn: 317 118 loop: 1))
		)
		(NormalEgo)
		(if nearSteps
			(ego setPri: 11 observeControl: 16384 init:)
		else
			(ego observeControl: 8192 init:)
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(cond 
				((& (ego onControl:) $0002) (curRoom newRoom: 305))
				((& (ego onControl:) $0008) (curRoom newRoom: 220))
				((& (ego onControl:) $0010)
					(if nearSteps
						(curRoom newRoom: 400)
					else
						(curRoom newRoom: 253)
					)
				)
			)
		)
		(cond 
			((== nearSteps -1))
			((& (ego onControl:) $1000) (= nearSteps 1))
			((& (ego onControl:) $0800) (= nearSteps 0))
		)
		(if (== nearSteps 1)
			(ego setPri: 11 observeControl: 16384 ignoreControl: 8192)
		)
		(if (== nearSteps 0)
			(ego
				setPri: -1
				ignoreControl: 16384 16
				observeControl: 8192
			)
		)
		(if
			(and
				(& (ego onControl:) $0002)
				(or musicLoop (!= gCurRoomNum 0))
			)
			(if (not nearOffice)
				(= nearOffice 1)
				(ego
					posn: (ego xLast?) (ego yLast?)
					setMotion: 0
					observeControl: 2
				)
				(if musicLoop (Print 250 0) else (Print 250 1))
				(Animate (cast elements?) 0)
			)
		else
			(= nearOffice 0)
		)
		(if
			(and
				(& (ego onControl:) $0010)
				nearSteps
				(!= gCurRoomNum 11)
				(!= gCurRoomNum 0)
			)
			(if (not nearCasino)
				(= nearCasino 1)
				(ego
					posn: (ego xLast?) (ego yLast?)
					setMotion: 0
					observeControl: 16
				)
				(Print 250 2)
				(Animate (cast elements?) 0)
			)
		else
			(= nearCasino 0)
		)
		(if (& (ego onControl:) $0004)
			(if (not cantGoThere)
				(= cantGoThere 1)
				(ShakeScreen 1 2)
				(Print 250 3)
				(Print 250 4 #at -1 144)
			)
		else
			(= cantGoThere 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst 22)) (= cycles 25))
			)
			(1
				(Format @plot 250 15)
				(= seconds (localproc_000c))
				(aCredit1 view: 53 posn: 0 156 setCycle: Fwd init:)
				(= seconds 13)
			)
			(2
				(Format @plot 250 16)
				(= seconds (localproc_000c))
			)
			(3
				(Format @plot 250 17)
				(= seconds (localproc_000c))
			)
			(4
				(Bset 22)
				(Format @plot 250 18)
				(= seconds (localproc_000c))
			)
			(5
				(aCredit1 dispose:)
				(= seconds 0)
			)
			(6
				(Ok)
				(HandsOff)
				(= nearSteps -1)
				(ego illegalBits: 0 setMotion: MoveTo 278 134 self)
			)
			(7
				(ego
					view: 709
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(8
				(Print 250 19 #icon 2 0 0)
				(= sharpenCycles 0)
				(= seconds 2)
				(= currentStatus (theGame setSpeed: 6))
			)
			(9
				(orchidSeconds number: 250 loop: 1 play:)
				(ego view: 251 cel: 0 setCycle: End)
				(= cycles 7)
				(if (< (++ sharpenCycles) 11) (-- state))
			)
			(10
				((Inv at: 2) view: 21)
				(Format ((Inv at: 2) name?) 250 20)
				(orchidSeconds stop:)
				(theGame setSpeed: currentStatus changeScore: 50)
				(ego view: 709 loop: 0 setCel: 255 setCycle: Beg self)
			)
			(11
				(NormalEgo)
				(= nearSteps 0)
				(Print 250 21)
				(Print 250 22)
			)
		)
	)
	
	(method (handleEvent event)
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
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if
				(and
					(> (event x?) 180)
					(< (event x?) 243)
					(> (event y?) 107)
					(< (event y?) 152)
				)
				(event claimed: 1)
				(switch theCursor
					(2 (Print 250 6))
					(998 (Print 250 12))
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 1)
					(< (event x?) 319)
					(> (event y?) 21)
					(< (event y?) 79)
				)
				(event claimed: 1)
				(switch theCursor
					(998 (Print 250 14))
					(else  (event claimed: 0))
				)
			)
			(if (proc0_27 275 317 101 137 event)
				(event claimed: 1)
				(switch theCursor
					(998 (Print 250 13))
					(2
						(cond 
							((not (ego has: 2)) (DontHave))
							((== ((Inv at: 2) view?) 21) (ItIs))
							((not nearSteps) (Print 250 8))
							((!= gCurRoomNum 0) (GoodIdea))
							(else (self changeState: 6))
						)
					)
					(999
						(ego setMotion: MoveTo 322 100)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 93)
					(< (event x?) 214)
					(> (event y?) 175)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 146 195)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 312)
					(< (event x?) 318)
					(> (event y?) 103)
					(< (event y?) 151)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 319 126)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 0)
					(< (event x?) 10)
					(> (event y?) 104)
					(< (event y?) 124)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -4 115)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 0)
					(< (event x?) 7)
					(> (event y?) 151)
					(< (event y?) 166)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -5 172)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 290)
					(< (event x?) 319)
					(> (event y?) 78)
					(< (event y?) 114)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 322 115)
					)
					(else  (event claimed: 0))
				)
			)
		)
	)
)

(instance aFountain of Prop
	(properties
		y 148
		x 194
		view 250
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self isExtra: 1 setCycle: Fwd setPri: 11)
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 240
		view 252
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors:)
	)
)

(instance aCredit2 of Prop
	(properties
		y 154
		x 240
		view 252
		loop 1
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
			(0 (= seconds 3))
			(1
				(aCredit1 setCycle: End)
				(= cycles 13)
			)
			(2
				(aCredit2 setCycle: End)
				(= cycles 16)
			)
			(3
				(aCredit2 setCycle: Beg self)
			)
			(4
				(aCredit2 loop: 2 setCycle: End)
				(= cycles 16)
			)
			(5
				(aCredit2 setCycle: Beg self)
			)
			(6
				(aCredit2 loop: 3 setCycle: End)
				(= cycles 16)
			)
			(7
				(aCredit2 setCycle: Beg self)
			)
			(8
				(aCredit2 loop: 4 setCycle: End)
				(= cycles 16)
			)
			(9
				(Bset 29)
				(aCredit1 setCycle: Beg)
				(aCredit2 setCycle: Beg self)
			)
			(10
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)

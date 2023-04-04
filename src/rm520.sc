;;; Sierra Script 1.0 - (do not remove this comment)
(script# 520)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm520 0
)

(local
	drownCycles
	[msgBuf 50]
	[titleBuf 22]
)
(instance rm520 of Rm
	(properties
		picture 520
		east 523
		south 510
	)
	
	(method (init)
		(Load rsVIEW 511)
		(Load rsVIEW 812)
		(Load rsVIEW 813)
		(Load rsVIEW 521)
		(Load rsSOUND 4)
		(Load rsSOUND 6)
		(if (ego has: 15) (Load rsVIEW 15))
		(super init:)
		(self setScript: RoomScript)
		(aRock1 init:)
		(aRock2 init:)
		(aRock3 init:)
		(aRock4 init:)
		(aRock5 init:)
		(cond 
			((== gCurRoomNum 12)
				(ego posn: 69 188)
				(= gCurRoomNum 1003)
				(RoomScript changeState: 1)
			)
			((== prevRoomNum 523) (NormalEgo 1))
			(else (ego posn: 181 188) (NormalEgo 3))
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
			(
			(and (& (ego onControl: 1) $0200) (== gCurRoomNum 0)) (self changeState: 1))
			(
			(and (== gCurRoomNum 1003) (< 8 (++ drownCycles)))
				(= drownCycles 0)
				(ego
					setLoop: (+ (Random 0 1) (* 2 (< (ego y?) 107)))
				)
			)
			(
			(and (& (ego onControl:) $0002) (== gCurRoomNum 0)) (self changeState: 12))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if (!= gCurRoomNum 1003)
					(= gCurRoomNum 1003)
					(orchidSeconds stop:)
					(gTheMusic number: 6 loop: -1 play:)
				)
				(HandsOff)
				(ego
					view: 812
					setLoop: 0
					setStep: 1 3
					setCycle: Fwd
					setPri: 8
					illegalBits: 0
				)
				(if (> (ego y?) 137)
					(ego posn: 58 (ego y?) setMotion: MoveTo 58 137 self)
				else
					(ego posn: (- (ego x?) 20) (ego y?))
					(self cue:)
				)
			)
			(2
				(if (> (ego y?) 100)
					(ego setMotion: MoveTo 86 100 self)
				else
					(self cue:)
				)
			)
			(3
				(ego setMotion: MoveTo 105 83 self)
			)
			(4
				(= gCurRoomNum 1001)
				(orchidSeconds stop:)
				(gTheMusic number: 4 loop: 1 play:)
				(ego setPri: 2 setLoop: 2 setCel: 0 setCycle: End self)
			)
			(5
				(ego setStep: 1 8 setMotion: MoveTo (ego x?) 180 self)
			)
			(6
				(cls)
				(if global64
					(NormalEgo)
					(= gCurRoomNum 0)
					(ego posn: 178 100)
				else
					(theGame setScript: (ScriptID 40))
					((ScriptID 40)
						caller: 522
						register: (Format @msgBuf 520 30)
						next: (Format @titleBuf 520 31)
					)
				)
			)
			(7
				(HandsOff)
				(= gCurRoomNum 3)
				(ego
					view: 511
					cycleSpeed: 2
					setLoop: 0
					cel: 0
					setCycle: End self
				)
			)
			(8
				(ego setLoop: 1 cel: 0 setCycle: Fwd)
				(= cycles
					(* 2 (ego cycleSpeed?) 4 (- (NumCels ego) 1))
				)
			)
			(9
				(ego setLoop: 0 setCel: 255 setCycle: Beg self)
			)
			(10 (= seconds 3))
			(11
				(theGame changeScore: 42)
				(Bset 6)
				(NormalEgo 1)
				(= gCurRoomNum 0)
				(Print 520 32)
			)
			(12
				(HandsOff)
				(Print
					(Format @msgBuf 520 33 filthStr)
					#at
					-1
					10
					#dispose
				)
				(= gCurRoomNum 2)
				(orchidSeconds stop:)
				(gTheMusic number: 4 loop: 1 play:)
				(ego
					view: 813
					setLoop: 0
					cel: 0
					illegalBits: 0
					ignoreActors:
					setPri: 2
					setCycle: End self
				)
			)
			(13
				(ego setStep: 1 8 setMotion: MoveTo (ego x?) 188 self)
				(if global64 (= state 5))
			)
			(14 (curRoom newRoom: 525))
			(15
				(HandsOff)
				(Ok)
				(theGame changeScore: 15)
				(Bset 72)
				(ego
					view: 521
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(16
				(ego cel: 3 setCycle: Beg self)
			)
			(17 (NormalEgo))
			(18
				(HandsOff)
				(Ok)
				(theGame changeScore: 40)
				(= gCurRoomNum 13)
				(ego illegalBits: 0 setMotion: MoveTo 213 104 self)
			)
			(19
				(ego
					view: 521
					loop: 1
					cel: 0
					cycleSpeed: 1
					setPri: 10
					setCycle: End self
				)
			)
			(20 (= cycles 11))
			(21
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(22 (= cycles 11))
			(23
				(Print 520 34 #icon 15 0 0 #at -1 10)
				(ego setLoop: 3 cel: 0 posn: 212 71 setCycle: End self)
			)
			(24
				(ego setPri: 2)
				(= cycles 5)
			)
			(25
				(ego
					setStep: 1 1
					setMotion: MoveTo (ego x?) (+ 30 (ego y?)) self
				)
			)
			(26
				(Print 520 35 #at -1 10)
				(curRoom newRoom: 525)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			(
				(or
					(Said 'get/drink,water')
					(Said 'drink')
					(Said 'drink/water')
				)
				(cond 
					((Btst 6) (Print 520 0))
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0200)) (NotClose))
					(else (self changeState: 7))
				)
			)
			((Said 'use,attach/bra')
				(cond 
					((not (ego has: 16)) (DontHave))
					((!= gCurRoomNum 0) (GoodIdea))
					(else (Print 520 1))
				)
			)
			((Said 'use/hose') (if (not (ego has: 15)) (DontHave) else (Print 520 2)))
			((Said 'drain,(off<get),(get<off)/hose')
				(cond 
					((InRoom 15 484) (Print 520 3))
					((InRoom 15 -1) (DontHave))
					((Btst 72) (Print 520 4))
					((!= gCurRoomNum 0) (GoodIdea))
					(else (self changeState: 15))
				)
			)
			((Said '(backdrop<on),wear/hose')
				(cond 
					((InRoom 15 484) (Print 520 3))
					((InRoom 15 -1) (DontHave))
					((!= gCurRoomNum 0) (GoodIdea))
					((not (Btst 72)) (Print 520 5))
					(else (Print 520 6) (theGame changeScore: -15) (Bclr 72))
				)
			)
			((Said 'attach/hose>')
				(cond 
					((not (ego has: 15)) (Print 520 7))
					((not (Btst 72)) (Print 520 8))
					((Said '//!*') (Print 520 9))
					((not (Said '//boulder')) (Print 520 10))
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0010)) (Print 520 11))
					(else (self changeState: 18))
				)
				(event claimed: 1)
			)
			((Said 'look>')
				(cond 
					((Said '/palm') (Print 520 12))
					((Said '/creek') (Print 520 13))
					((Said '/boulder,boob')
						(if (& (ego onControl:) $0010)
							(Print 520 14)
						else
							(Print 520 15)
						)
					)
					((Said '/ledge,cliff,canyon') (Print 520 16))
					((Said '/cascade,cascade,water') (Print 520 17) (Print 520 18 #at -1 144))
					((Said '[/area]') (Print 520 19))
				)
			)
			((Said '(up<climb),climb/boulder,arch') (Print 520 20))
			(
				(or
					(Said '(climb,go)<(down,above)')
					(Said 'decrease/i')
				)
				(Print 520 21)
			)
			((Said 'climb') (Print 520 22) (Print 520 23 #at -1 144))
			((Said 'drag,grasp,get/bush,hemp') (Print 520 24))
			((Said 'get,use/palm') (Print 520 25))
			((Said '/bush') (Print 520 26))
			((Said '/arch') (Print 520 27))
			((Said '/flower') (Print 520 28))
			((Said 'jump') (Print 520 29))
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 187)
						(< (event x?) 240)
						(> (event y?) 71)
						(< (event y?) 94)
					)
					(event claimed: 1)
					(switch theCursor
						(15
							(cond 
								((not (ego has: 15)) (Print 520 7))
								((not (Btst 72)) (Print 520 8))
								((!= gCurRoomNum 0) (GoodIdea))
								((not (& (ego onControl:) $0010)) (Print 520 11))
								(else
									(= gTheCursor 900)
									(theGame setCursor: 998 (HaveMouse))
									(self changeState: 18)
								)
							)
							(event claimed: 1)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 1)
						(< (event x?) 111)
						(> (event y?) 124)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								((Btst 6) (Print 520 0))
								((!= gCurRoomNum 0) (GoodIdea))
								((not (& (ego onControl:) $0200)) (NotClose))
								(else (self changeState: 7))
							)
						)
						(998
							(Print 520 17)
							(Print 520 18 #at -1 144)
							(event claimed: 1)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 120)
						(< (event x?) 319)
						(> (event y?) 21)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 520 19))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 313)
						(< (event x?) 319)
						(> (event y?) 97)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 321 130)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 107)
						(< (event x?) 319)
						(> (event y?) 176)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 195 192)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aRock1 of Prop
	(properties
		y 160
		x -18
		view 520
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd isExtra: 1 ignoreActors:)
	)
)

(instance aRock2 of Prop
	(properties
		y 187
		x 56
		view 520
		loop 1
		cel 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd isExtra: 1 ignoreActors:)
	)
)

(instance aRock3 of Prop
	(properties
		y 179
		x 37
		view 520
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd isExtra: 1 ignoreActors:)
	)
)

(instance aRock4 of Prop
	(properties
		y 118
		x -5
		view 520
		loop 2
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd isExtra: 1 ignoreActors:)
	)
)

(instance aRock5 of Prop
	(properties
		y 176
		x 12
		view 520
		loop 2
		cel 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd isExtra: 1 ignoreActors:)
	)
)

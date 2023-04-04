;;; Sierra Script 1.0 - (do not remove this comment)
(script# 540)
(include sci.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Wander)
(use Jump)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm540 0
	RoomScript 1
)

(local
	pigAppears
	[msgBuf 33]
	[titleBuf 22]
)
(instance rm540 of Rm
	(properties
		picture 540
		horizon 78
		north 550
		south 530
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(if (== prevRoomNum 550)
			(ego posn: 233 80)
		else
			(ego posn: 233 188)
		)
		(if (not (Btst 68))
			(Load rsVIEW 541)
			(Load rsVIEW 542)
			(Load rsVIEW 543)
			(Load rsSCRIPT 991)
			(Load rsSCRIPT 970)
			(Load rsSOUND 540)
			(Load rsSOUND 541)
			(Load rsSOUND 12)
			(Load rsSOUND 561)
			(Load rsSOUND 530)
			(aPig init:)
			(aBra init:)
		)
		(= global66 803)
		((Inv at: 17) view: 31)
		(= gCurRoomNum 0)
		(NormalEgo 3)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
		(LoadMany 0 970)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 7)
				(== 541 (ego view?))
				(== 2 (ego loop?))
				(== 1 (ego cel?))
			)
			(orchidSeconds number: 530 loop: 1 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(Bset 73)
				(if (not (Btst 75))
					(Bset 75)
					(theGame changeScore: 5)
				)
				(Ok)
				(ego
					view: 541
					setLoop: 0
					cel: 0
					illegalBits: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(2
				(Print 540 25 #at -1 10)
				(NormalEgo 3)
			)
			(3
				(HandsOff)
				(Ok)
				(Bclr 73)
				(Bclr 74)
				(ego
					view: 541
					setLoop: 0
					setCel: 255
					illegalBits: 0
					cycleSpeed: 1
					setCycle: Beg self
				)
			)
			(4
				(Print 540 26)
				(NormalEgo 3)
			)
			(5
				(HandsOff)
				(Bset 74)
				(if (not (Btst 76))
					(Bset 76)
					(theGame changeScore: 45)
				)
				(Ok)
				(ego
					view: 541
					setLoop: 1
					cel: 0
					illegalBits: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(6
				(Print 540 27)
				(NormalEgo 3)
				(Print 540 28 #at -1 144)
			)
			(7
				(HandsOff)
				(Ok)
				(Bclr 73)
				(gTheMusic stop:)
				(= currentStatus (theGame setSpeed: 6))
				(= gCurRoomNum 540)
				(ego
					view: 541
					setLoop: 2
					illegalBits: 0
					cel: 0
					setCycle: Fwd
				)
				(= cycles (* 5 (- (NumCels ego) 1)))
			)
			(8
				(NormalEgo 3)
				(HandsOff)
				(aBra
					ignoreHorizon:
					posn: (+ (ego x?) 23) (- (ego y?) 43)
					setLoop: 3
					setCycle: Fwd
					setStep: 8 8
					setMotion: JumpTo (- (aPig x?) 2) (- (aPig y?) 13) self
				)
				(PigScript changeState: 9)
			)
			(9
				(PigScript cue:)
				(aBra dispose:)
				(if (Btst 74)
					(ego put: 16 -1 put: 19 -1)
					(self changeState: 11)
				else
					(= seconds 3)
				)
			)
			(10
				(Print 540 29)
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 543
					register: (Format @msgBuf 540 30)
					next: (Format @titleBuf 540 31)
				)
			)
			(11 (= cycles 0) (= seconds 3))
			(12
				(Print 540 32 #at -1 10)
				(NormalEgo 3)
				(= gCurRoomNum 0)
				(Print 540 33 #at -1 144)
				(gTheMusic number: 599 loop: global72 play:)
				(Bset 68)
				(theGame changeScore: 100)
				(theGame setSpeed: currentStatus)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'use/bra,coconut') (Print 540 0))
			((Said 'use/marker') (Print 540 1))
			((and (ego has: 12) (Said '/key')) (Print 540 2))
			((Said 'climb/palm') (Print 540 3))
			((Said '(backdrop<on),wear/bra')
				(cond 
					((InRoom 16 484) (Print 540 4))
					((InRoom 16 -1) (DontHave))
					((!= gCurRoomNum 0) (GoodIdea))
					((not (Btst 73)) (Print 540 5))
					((Btst 74) (Print 540 6))
					(else (self changeState: 3))
				)
			)
			((Said 'drain,(off<get),(get<off)/bra')
				(cond 
					((InRoom 16 484) (Print 540 4))
					((InRoom 16 -1) (DontHave))
					((Btst 73) (Print 540 7))
					((!= gCurRoomNum 0) (GoodIdea))
					(else (self changeState: 1))
				)
			)
			(
				(and
					(Btst 74)
					(Said 'drain,(out<get),(get<out)/coconut')
				)
				(Bclr 74)
				(Print 540 8)
			)
			((Said 'backdrop/coconut/bra')
				(cond 
					((not (ego has: 16)) (Print 540 9))
					((not (ego has: 19))
						(Print 540 10)
						(if (>= global88 3) (Print 540 11 #at -1 144))
					)
					((!= gCurRoomNum 0) (GoodIdea))
					((not (Btst 73)) (Print 540 12) (Print 540 13 #at -1 144))
					((Btst 74) (Print 540 14))
					(else (self changeState: 5))
				)
			)
			(
				(or
					(Said 'use/bra/throw')
					(Said 'throw/coconut/animal')
					(Said 'throw/bra')
				)
				(cond 
					((InRoom 16 484) (Print 540 4))
					((not (ego has: 16)) (DontHave))
					((not (Btst 73)) (Print 540 15))
					((!= gCurRoomNum 0) (GoodIdea))
					((!= (PigScript state?) 2) (Print 540 16))
					(else (self changeState: 7))
				)
			)
			((Said 'throw/coconut')
				(if (ego has: 19)
					(Print 540 17)
				else
					(Print 540 10)
					(if (> global88 1) (Print 540 18 #at -1 144))
				)
			)
			((Said 'throw>')
				(cond 
					((Said '[/!*]') (Print 540 19))
					((Said '/*[/!*]') (Print 540 20))
				)
			)
			((Said 'climb,crawl') (Print 540 21))
			((Said 'look>')
				(cond 
					((Said '/creek') (Print 540 22))
					((Said '[/area]') (Print 540 23) (Print 540 24 #at -1 144))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if (proc0_26 aPig (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(16
							(event claimed: 1)
							(cond 
								((InRoom 16 484) (Print 540 4))
								((not (ego has: 16)) (DontHave))
								((not (Btst 73)) (Print 540 15))
								((!= gCurRoomNum 0) (GoodIdea))
								((!= (PigScript state?) 2) (Print 540 16))
								(else (self changeState: 7))
							)
						)
						(998 (Print 540 48))
						(997 (Print 540 47))
						(995 (Print 540 46))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(proc0_26 ego (event x?) (event y?))
						(== theCursor 16)
					)
					(event claimed: 1)
					(switch theCursor
						(16
							(cond 
								((InRoom 16 484) (Print 540 4))
								((InRoom 16 -1) (DontHave))
								((Btst 73) (Print 540 7))
								((!= gCurRoomNum 0) (GoodIdea))
								(else
									(= gTheCursor 900)
									(theGame setCursor: 998 (HaveMouse))
									(self changeState: 1)
								)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 153)
						(< (event x?) 319)
						(> (event y?) 53)
						(< (event y?) 69)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 540 22))
						(16 (event claimed: 1))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 0)
						(< (event x?) 225)
						(> (event y?) 70)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(Print 540 23)
							(Print 540 24 #at -1 144)
						)
						(16 (event claimed: 1))
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aBra of Act
	(properties
		y 999
		x 999
		view 541
		illegalBits $0000
	)
)

(instance aPig of Act
	(properties
		y 111
		x 288
		view 540
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			setLoop: 0
			setPri: 1
			setCycle: Fwd
			setStep: 4 4
			setScript: PigScript
		)
	)
)

(instance PigScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((== gCurRoomNum 540))
			((and (< state 4) (& (ego onControl:) $0002)) (self changeState: 4))
			(
				(and
					(& (ego onControl: 1) $0010)
					(or (== state 1) (== state 2))
				)
				(self changeState: 3)
			)
			(
				(and
					(& (ego onControl: 1) $0004)
					(or (== state 0) (== state 3))
				)
				(self changeState: 1)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(gTheMusic number: 540 loop: -1 play:)
				(aPig setMotion: MoveTo 216 97 self)
			)
			(2
				(if (not pigAppears)
					(= pigAppears 1)
					(Print 540 34 #at 10 5 #width 290)
				)
				(aPig
					illegalBits: -3
					setMotion: Wander
					setPri: -1
					setStep: 2 2
				)
			)
			(3
				(gTheMusic fade:)
				(aPig
					illegalBits: 0
					setPri: 1
					setMotion: MoveTo 288 111 self
				)
				(= state -1)
			)
			(4
				(= gCurRoomNum 1001)
				(HandsOff)
				(Print 540 35 #at -1 10)
				(orchidSeconds stop:)
				(gTheMusic number: 541 loop: 1 play:)
				(aPig
					illegalBits: 0
					ignoreActors:
					setPri: -1
					setLoop: 1
					setCel: 0
					setCycle: End
					setStep: 4 10
					setMotion:
						MoveTo
						(- (aPig x?) (/ (- (aPig x?) (ego x?)) 2))
						-10
						self
				)
			)
			(5
				(aPig
					setLoop: 2
					setCycle: Fwd
					setMotion: MoveTo (ego x?) (- (ego y?) 40) self
				)
			)
			(6
				(gTheMusic number: 12 loop: 1 play:)
				(ego view: 542 cel: 0 setCycle: End)
				(aPig setMotion: MoveTo (ego x?) (ego y?) self)
			)
			(7
				(ego stopUpd:)
				(aPig setLoop: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(8
				(if (>= global88 3)
					(Print 540 36 #at -1 10)
					(Print 540 37 #at -1 144)
				else
					(Print 540 38 #at -1 10)
					(Print 540 39 #at -1 10)
				)
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 543
					register: (Format @msgBuf 540 40)
					next: (Format @titleBuf 540 41)
				)
			)
			(9
				(= seconds (= cycles 0))
				(aPig setMotion: 0)
			)
			(10
				(= seconds (= cycles 0))
				(gTheMusic stop:)
				(if (Btst 74)
					(orchidSeconds number: 12 loop: 1 play:)
					(aPig
						illegalBits: 0
						setLoop: 3
						cel: 0
						setStep: 4 4
						setCycle: End
						setMotion: JumpTo 225 69 self
					)
				else
					(self changeState: 1)
				)
			)
			(11
				(orchidSeconds number: 561 loop: 1 play:)
				(aPig setLoop: 4 cel: 0 setCycle: End self)
			)
			(12
				(RoomScript changeState: 11)
				(aPig setScript: 0 dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((Said '//animal>')
				(cond 
					((> (aPig x?) 275) (Print 540 42) (event claimed: 1))
					((Said 'throw/*>')
						(if (Said '/bra')
							(event claimed: 0)
						else
							(Print 540 43)
							(event claimed: 1)
						)
					)
					(else (Print 540 44) (event claimed: 1))
				)
			)
			((Said '/animal>')
				(cond 
					((> (aPig x?) 280) (Print 540 42) (event claimed: 1))
					((Said 'feed/') (Print 540 45))
					((Said 'attack,carve/') (Print 540 46))
					((Said 'address') (Print 540 47))
					((Said 'look/') (Print 540 48))
					(else (Print 540 49) (event claimed: 1))
				)
			)
		)
	)
)

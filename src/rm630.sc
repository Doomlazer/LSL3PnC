;;; Sierra Script 1.0 - (do not remove this comment)
(script# 630)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Wander)
(use Follow)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm630 0
)
(synonyms
	(cable cable)
)

(instance rm630 of Rm
	(properties
		picture 630
		horizon 1
		east 640
	)
	
	(method (init)
		(Load rsVIEW 720)
		(Load rsVIEW 631)
		(Load rsVIEW global66)
		(Load rsSOUND 630)
		(Load rsSOUND 4)
		(Load rsSOUND 5)
		(Load rsSOUND 631)
		(Load rsSOUND 699)
		(Load rsSCRIPT 969)
		(Load rsSCRIPT 970)
		(super init:)
		(aGeneratorBottom init:)
		(aGeneratorTop init:)
		(aSwitch init:)
		(aLarry init:)
		(NormalEgo)
		(ego
			illegalBits: 0
			view: 632
			setLoop: 0
			setPri: 13
			cel: 0
			posn: 7 183
			init:
			baseSetter: SQ3Base
		)
		(self setScript: RoomScript)
		(= gCurRoomNum 630)
		(gTheMusic number: 630 loop: global72 play:)
	)
	
	(method (newRoom newRoomNumber)
		(DisposeScript 970)
		(super newRoom: newRoomNumber)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== gCurRoomNum 630)
			(aLarry
				brLeft: (- (aLarry x?) 4)
				brRight: (+ (aLarry x?) 4)
			)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 19 171 setCycle: End self)
			)
			(1
				(ego
					posn: 35 149
					setLoop: 1
					cel: 1
					setCycle: Fwd
					cycleSpeed: 4
					moveSpeed: 1
					setStep: 1 1
					setMotion: MoveTo 72 138 self
				)
			)
			(2
				(Printf 630 10 filthStr)
				(ego
					observeControl: 8192
					baseSetter: SQ3Base
					setMotion: MoveTo 187 99 self
				)
				(aLarry setMotion: MoveTo 6 185 self)
			)
			(3
				(aLarry
					view: 631
					setLoop: 0
					cel: 0
					setMotion: MoveTo 18 173
					setCycle: End self
				)
			)
			(4
				(Print 630 11)
				(aLarry
					posn: 31 150
					setLoop: 1
					cel: 1
					setCycle: Fwd
					cycleSpeed: 4
					moveSpeed: 1
					setStep: 1 1
					setMotion: MoveTo 153 41 self
				)
				(HandsOn)
				(ego
					illegalBits: 0
					observeControl: -32768 8192
					baseSetter: SQ3Base
				)
			)
			(5
				(aLarry observeControl: -32768 16384 setMotion: Wander 22)
			)
			(6
				(Ok)
				(HandsOff)
				(theGame changeScore: 40)
				(aSwitch setCycle: End self)
				(aLarry setMotion: MoveTo 148 (aLarry y?))
			)
			(7
				(aSwitch stopUpd:)
				(aGeneratorTop setCel: 0 stopUpd:)
				(if (== global72 -1)
					(gTheMusic number: 631 loop: 1 play: self)
				else
					(= cycles 22)
				)
			)
			(8
				(Print 630 12)
				(if (== global72 -1) (= seconds 2) else (= cycles 22))
			)
			(9
				(= gCurRoomNum 2)
				(Print 630 13)
				(gTheMusic number: 4 loop: 1 play:)
				(ego
					baseSetter: 0
					illegalBits: 0
					cycleSpeed: 0
					moveSpeed: 0
					setStep: 3 4
					setMotion: MoveTo (ego x?) 152 self
				)
				(aLarry
					illegalBits: 0
					cycleSpeed: 0
					moveSpeed: 0
					setStep: 3 4
					setMotion: MoveTo (aLarry x?) 152 self
				)
			)
			(10
				(ego posn: (ego x?) 181 loop: 2 cel: 0 setCycle: End)
			)
			(11
				(aLarry
					posn: (aLarry x?) 181
					loop: 2
					cel: 0
					setCycle: End
				)
				(= seconds 3)
			)
			(12
				(ego cycleSpeed: 1 loop: 3 cel: 0 setCycle: End)
				(aLarry cycleSpeed: 1 loop: 3 cel: 0 setCycle: End self)
			)
			(13
				(aLarry loop: 4 setCycle: Fwd)
				(ego loop: 4 setCycle: Fwd)
				(gTheMusic number: 5 loop: 1 play: self)
			)
			(14
				(= gCurRoomNum 0)
				(NormalEgo 3)
				(NormalActor aLarry 3 720)
				(aLarry setMotion: Follow ego 28)
				(gTheMusic number: 699 loop: global72 play:)
				(Print 630 14)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			(
			(Said 'cable,attach,(on<drag)/cable,equipment,handle') (if (== gCurRoomNum 0) (ItIs) else (Print 630 0)))
			(
				(Said
					'disconnect,drain,(off<drag),cease,drag/cable,equipment,handle'
				)
				(cond 
					((!= gCurRoomNum 630) (GoodIdea))
					((not (ego inRect: 190 126 261 154)) (NotClose))
					((or (< (ego cel?) 6) (> (ego cel?) 9)) (Print 630 1))
					(else (RoomScript changeState: 6))
				)
			)
			((Said 'get/cable,cable') (Print 630 2))
			((Said 'look>')
				(cond 
					((Said '/cable,cable,cable')
						(if (== gCurRoomNum 630)
							(Print 630 3)
						else
							(Print 630 4)
						)
					)
					((Said '/larry') (Print 630 5))
					((Said '/equipment,equipment')
						(if (== gCurRoomNum 630)
							(Print 630 6)
						else
							(Print 630 7)
						)
					)
					((Said '[/area]') (Print 630 8) (if (== gCurRoomNum 630) (Print 630 9)))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
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
							(Print 630 8)
							(if (== gCurRoomNum 630) (Print 630 9))
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 309)
						(< (event x?) 319)
						(> (event y?) 154)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 321 175)
						)
						(else  (event claimed: 0))
					)
				)
				(if
				(proc0_26 aGeneratorBottom (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(if (== gCurRoomNum 630)
								(Print 630 3)
							else
								(Print 630 4)
							)
						)
						(995
							(cond 
								((!= gCurRoomNum 630) (GoodIdea))
								((not (ego inRect: 190 126 261 154)) (NotClose))
								((or (< (ego cel?) 6) (> (ego cel?) 9)) (Print 630 1))
								(else (RoomScript changeState: 6))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aLarry (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998 (Print 630 5))
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aGeneratorBottom of View
	(properties
		y 149
		x 227
		view 630
	)
	
	(method (init)
		(super init:)
		(self setPri: 5 ignoreActors: stopUpd:)
	)
)

(instance aGeneratorTop of Prop
	(properties
		y 133
		x 235
		view 630
		loop 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 6 setCycle: Fwd)
	)
)

(instance aSwitch of Prop
	(properties
		y 153
		x 213
		view 630
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 7 ignoreActors: stopUpd:)
	)
)

(instance aLarry of Act
	(properties
		y 184
		x -28
		view 720
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd setPri: 5 ignoreActors:)
	)
)

(instance SQ3Base of Code
	(properties)
	
	(method (doit &tmp temp0)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) (ego yStep?)))
		(ego brLeft: (- (ego x?) 18))
		(ego brRight: (+ (ego x?) 18))
	)
)

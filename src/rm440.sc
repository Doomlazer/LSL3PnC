;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm440 0
)

(local
	[local0 2]
)
(procedure (localproc_000c)
	(systemWindow color: 12 back: 8)
	(Print &rest #at 20 -1 #title {Cherri says} #width 150)
	(systemWindow color: 0 back: 8)
)

(procedure (localproc_0042)
	(systemWindow color: 9 back: 8)
	(Print &rest #at 180 -1 #title {You say} #width 120)
	(systemWindow color: 0 back: 8)
)

(instance rm440 of Rm
	(properties
		picture 440
		east 420
	)
	
	(method (init)
		(SL disable:)
		(TheMenuBar hide:)
		(Load rsVIEW 440)
		(Load rsVIEW 444)
		(super init:)
		(self setScript: RoomScript)
		(NormalEgo 1)
		(ego
			viewer: shadowViewer
			posn: 315 151
			observeBlocks: theCage
			init:
		)
		(User canInput: 0 mapKeyToDir: 0)
		(cond 
			((not (Btst 35))
				(Bset 35)
				(Bset 5)
				(Load rsVIEW 443)
				(Load rsVIEW 442)
				(Load rsSOUND 8)
				(Load rsSOUND 10)
				(Load rsPIC 99)
				(HandsOff)
				(aCherri init: setCycle: Walk ignoreActors:)
				(RoomScript changeState: 1)
			)
			((== gCurRoomNum 11)
				(Load rsVIEW 441)
				(Load rsVIEW 445)
				(aLclothes ignoreActors: init:)
			)
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 7)
		(switch (= state newState)
			(0)
			(1
				(ego setMotion: MoveTo 155 151 self)
				(aCherri setMotion: MoveTo 210 151 self)
			)
			(2
				(localproc_000c 440 12)
				(aCherri
					view: 442
					setLoop: 0
					setMotion: MoveTo 94 151 self
				)
			)
			(3 (localproc_0042 440 13))
			(4
				(aCherri setLoop: 1 cel: 0)
				(= seconds 3)
			)
			(5
				(localproc_000c 440 14)
				(gTheMusic number: 8 loop: -1 play:)
				(= seconds 3)
			)
			(6
				(aCherri cycleSpeed: 1 setCycle: End self)
			)
			(7
				(aCherri setLoop: 2 cel: 0)
				(aHat ignoreActors: init: stopUpd:)
				(= seconds 3)
			)
			(8 (aCherri setCycle: End self))
			(9
				(aCherri setLoop: 3 cel: 0)
				(aTail ignoreActors: init: stopUpd:)
				(= seconds 3)
			)
			(10
				(aCherri setCycle: End self)
			)
			(11
				(aBra ignoreActors: init: stopUpd:)
				(= seconds 3)
			)
			(12
				(aCherri
					setLoop: 5
					setCycle: Walk
					setMotion: MoveTo 114 151 self
				)
			)
			(13
				(localproc_000c 440 15)
				(= seconds 3)
			)
			(14
				(Print 440 16)
				(Print 440 17)
				(= gCurRoomNum 1)
				(ego
					viewer: 0
					view: 442
					loop: 4
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(15
				(aLclothes ignoreActors: init: stopUpd:)
				(ego setLoop: 6 cel: 0 setCycle: Walk)
				(= seconds 2)
			)
			(16
				(aCherri loop: 7 cel: 0 setCycle: End)
				(ego setMotion: MoveTo 145 151 self)
			)
			(17
				(aCherri dispose:)
				(ego
					view: 443
					loop: 0
					cel: 0
					posn: 114 151
					setCycle: End self
				)
			)
			(18 (= seconds 3))
			(19
				(localproc_000c 440 18)
				(= seconds 3)
			)
			(20
				(if (< global88 1)
					(self changeState: 24)
				else
					(ego viewer: humpCycler loop: 1 cel: 0 setCycle: Fwd)
					(= seconds 3)
				)
			)
			(21
				(if (<= global88 2)
					(self changeState: 24)
				else
					(ego loop: 2)
					(= seconds 3)
				)
			)
			(22
				(if (<= global88 3)
					(self changeState: 24)
				else
					(ego loop: 3)
				)
				(= seconds 3)
			)
			(23
				(if (< global88 4)
					(self changeState: 24)
				else
					(ego loop: 4)
				)
				(= seconds 3)
			)
			(24
				(localproc_000c 440 19)
				(= seconds 3)
			)
			(25
				(localproc_0042 440 20)
				(= seconds 3)
			)
			(26
				(gTheMusic number: 10 loop: -1 play:)
				(curRoom drawPic: 99 2)
				(cast eachElementDo: #hide)
				(localproc_0042 440 21)
				(= cycles 11)
			)
			(27
				(localproc_0042 440 22)
				(= seconds 3)
			)
			(28
				(localproc_000c 440 23)
				(= seconds 3)
			)
			(29
				(Print 440 24)
				(= seconds 3)
			)
			(30
				(localproc_0042 440 25)
				(= seconds 3)
			)
			(31
				(Print 440 26 #at 10 5 #width 290)
				(= seconds 2)
			)
			(32
				(localproc_0042 440 27)
				(systemWindow color: gameHours back: oldSysTime)
				(curRoom newRoom: 431)
			)
			(33
				(Ok)
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 155 151 self)
			)
			(34
				(ego
					viewer: 0
					view: 445
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(35
				(aHat posn: 155 151 ignoreActors: init: stopUpd:)
				(ego setLoop: 1 cel: 0 setCycle: End self)
			)
			(36
				(aTail posn: 155 151 ignoreActors: init: stopUpd:)
				(ego setLoop: 2 cel: 0 setCycle: End self)
			)
			(37
				(aBra posn: 155 151 ignoreActors: init: stopUpd:)
				(ego loop: 3 cel: 0 setCycle: End self)
				(= cycles 9)
			)
			(38 (aLclothes dispose:))
			(39
				(= gameMinutes 6)
				(= global98 3)
				(= gCurRoomNum 18)
				(= global66 700)
				(NormalEgo 1 444)
				(ego viewer: shadowViewer)
				(theGame changeScore: 25)
				(= cycles 22)
			)
			(40 (Print 440 28))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'smell') (Print 440 0))
			(
				(or
					(Said 'naked')
					(Said
						'(alter<(out<of),from),(off<get),(get<off),drain/cloth,dress,cloth'
					)
					(Said 'dress<get')
					(Said 'get/dress')
					(Said
						'wear,get,(alter<in,to),(backdrop<on)//cloth,cloth[<leisure]'
					)
					(Said
						'wear,get,(alter<in,to),(backdrop<on)/cloth,cloth[<leisure]'
					)
				)
				(cond 
					((not (ego inRect: 121 127 199 159)) (NotClose))
					((!= gCurRoomNum 11) (Print 440 1) (Print 440 2) (Print 440 3))
					(else (self changeState: 33))
				)
			)
			((Said 'look>')
				(cond 
					((Said '/burn,ceiling') (Print 440 4))
					(
						(and
							(== gCurRoomNum 11)
							(Said '/backstage,carpet')
							(Print 440 5)
						)
					)
					((Said '/cloth,hat,bra,panties')
						(if (== gCurRoomNum 11)
							(Print 440 5)
						else
							(Print 440 6)
							(Print 440 7 #at -1 144)
						)
					)
					((Said '/prop,backdrop') (Print 440 8))
					((Said '/bottle') (Print 440 9))
					((Said '[/area,backstage]') (Print 440 10) (Print 440 11 #at -1 144))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 140)
						(< (event x?) 160)
						(> (event y?) 144)
						(< (event y?) 150)
					)
					(event claimed: 1)
					(switch theCursor
						(994
							(cond 
								((not (ego inRect: 121 127 199 159)) (NotClose))
								((!= gCurRoomNum 11) (Print 440 1) (Print 440 2) (Print 440 3))
								(else (self changeState: 33))
							)
						)
						(998
							(if (== gCurRoomNum 11)
								(Print 440 5)
							else
								(Print 440 6)
								(Print 440 7 #at -1 144)
							)
						)
						(995
							(cond 
								((not (ego inRect: 121 127 199 159)) (NotClose))
								((!= gCurRoomNum 11) (Print 440 1) (Print 440 2) (Print 440 3))
								(else (self changeState: 33))
							)
						)
					)
				)
				(if
					(and
						(> (event x?) 300)
						(< (event x?) 319)
						(> (event y?) 157)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 321 174)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aCherri of Act
	(properties
		y 151
		x 290
		view 446
		illegalBits $0000
	)
)

(instance aHat of View
	(properties
		y 144
		x 63
		view 440
	)
)

(instance aTail of View
	(properties
		y 148
		x 93
		view 440
		cel 1
	)
)

(instance aBra of View
	(properties
		y 143
		x 83
		view 440
		cel 2
	)
)

(instance aLclothes of View
	(properties
		y 145
		x 150
		view 440
		cel 3
	)
)

(instance theCage of Cage
	(properties
		top 144
		left -15
		bottom 212
		right 335
	)
)

(instance shadowViewer of Code
	(properties)
	
	(method (doit)
		(ego
			view:
				(cond 
					((not (& (ego onControl: 1) $0200)) global66)
					((== gCurRoomNum 11) 441)
					((!= gCurRoomNum 1) 444)
					(else (Print 440 29))
				)
		)
	)
)

(instance humpCycler of Code
	(properties)
	
	(method (doit)
		(if (not (Random 0 9)) (ego cycleSpeed: (Random 0 5)))
	)
)

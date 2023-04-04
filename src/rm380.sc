;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm380 0
)
(synonyms
	(use class do)
)

(local
	exerciseTimes
	local1
	local2
	local3
	oldEgoX
	oldEgoY
	oldEgoPriority
	poundsPumped
	legCurls
	pullUps
	barPulls
	currentActivity
	local12
)
(procedure (localproc_001a param1)
	(Ok)
	(HandsOff)
	(= currentActivity param1)
	(= oldEgoX (ego x?))
	(= oldEgoY (ego y?))
	(= oldEgoPriority (ego priority?))
	(switch (++ exerciseTimes)
		(1
			(Print
				{Toggle between left and right click to do the exercises.}
			)
		)
		(2 (Print 380 31))
		(3 (Print 380 32))
		(4 (Print 380 33))
		(5
			(Print 380 34)
			(= exerciseTimes 0)
		)
	)
	(ego viewer: 0 illegalBits: 0 ignoreActors:)
	(User mapKeyToDir: 0 canInput: 1)
)

(procedure (localproc_00c8)
	(= currentActivity 0)
	(= local12 0)
	(NormalEgo 2 (+ 704 currentEgo))
	(ego
		posn: oldEgoX oldEgoY
		setPri: oldEgoPriority
		viewer: egoViewer
	)
	(aBigEgo
		cycleSpeed: 0
		posn: 1234 1234
		setMotion: 0
		stopUpd:
	)
	(if
		(and
			(>= global117 barPulls)
			(>= global116 pullUps)
			(>= global114 poundsPumped)
			(>= global115 legCurls)
		)
		(HandsOff)
		(RoomScript changeState: 39)
	)
)

(procedure (localproc_013c &tmp [temp0 11])
	(= local2 0)
	(Display
		(Format
			@temp0
			380
			35
			global117
			global116
			global114
			global115
		)
		dsCOORD
		122
		82
		dsFONT
		999
		dsCOLOR
		12
		dsBACKGROUND
		0
	)
)

(instance rm380 of Rm
	(properties
		picture 380
		horizon 1
		east 370
	)
	
	(method (init &tmp temp0)
		(if (not currentEgo)
			(Load rsSOUND 380)
			(Load rsVIEW 383)
			(Load rsVIEW 384)
			(Load rsVIEW 385)
			(Load rsVIEW 386)
			(Load rsVIEW 387)
		)
		(super init:)
		(addToPics add: atpPullupHandles doit:)
		(aBigEgo init:)
		(aActor1 init:)
		(aCenterWeight init:)
		(aRoundBar init:)
		(aExtraBar init:)
		(aDumbbell init:)
		(aBarPullBarView init:)
		(aLegCurlBar init:)
		(self setScript: RoomScript)
		(= temp0 10)
		(= barPulls temp0)
		(= pullUps temp0)
		(= poundsPumped temp0)
		(= legCurls temp0)
		(NormalEgo 1 (+ 704 currentEgo))
		(ego posn: 270 175 setPri: 9 viewer: egoViewer init:)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0040) (curRoom newRoom: 370))
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 4)
		(switch (= state newState)
			(0)
			(1
				(localproc_001a 4)
				(ego
					view: 381
					posn: 201 135
					setLoop: 8
					cel: 0
					cycleSpeed: 1
				)
				(aActor1
					setStep: 3 5
					posn: 155 45
					view: 384
					setLoop: 2
					setPri: 12
				)
				(aBigEgo
					view: 384
					setLoop: 1
					setPri: 13
					posn: 155 80
					stopUpd:
				)
				(aLegCurlBar hide:)
				(self cue:)
			)
			(2 (= local2 1) (= local12 3))
			(3
				(= local12 1)
				(ego cel: 0 setCycle: End)
				(aActor1 setMotion: MoveTo 155 15 self)
				(aCenterWeight setMotion: MoveTo 153 136)
			)
			(4
				(= local12 4)
				(if local2
					(if (== (++ global115) legCurls)
						(Printf 380 17 global115)
						(RoomScript changeState: 7)
					)
					(localproc_013c)
				)
			)
			(5
				(= local12 2)
				(ego setCycle: Beg)
				(aActor1 setMotion: MoveTo 155 50 self)
				(aCenterWeight setMotion: MoveTo 153 166)
				(= state 1)
			)
			(6)
			(7
				(User mapKeyToDir: 1 canInput: 0 canControl: 0)
				(aCenterWeight setMotion: MoveTo 153 166 self)
				(aActor1 setMotion: MoveTo 155 50 self)
			)
			(8)
			(9
				(aLegCurlBar show:)
				(aCenterWeight stopUpd:)
				(localproc_00c8)
			)
			(10
				(localproc_001a 3)
				(ego
					cycleSpeed: 1
					view: 381
					posn: 216 163
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(11
				(aRoundBar hide:)
				(aBigEgo view: 382 cel: 0 setPri: 13 posn: 156 24)
				(aDumbbell hide:)
				(aActor1
					view: 380
					setLoop: 0
					setStep: 7 7
					setPri: 14
					posn: 155 34
				)
				(ego setPri: 13 setLoop: 2 posn: 219 159)
				(self cue:)
			)
			(12 (= local2 1) (= local12 3))
			(13
				(= local12 1)
				(ego cel: 0 setCycle: End self)
				(aActor1 setMotion: MoveTo 155 20)
				(aBigEgo setCycle: End)
			)
			(14
				(= local12 4)
				(if local2
					(if (== (++ global114) poundsPumped)
						(Printf 380 18 (* 100 global114))
						(RoomScript changeState: 17)
					)
					(localproc_013c)
				)
			)
			(15
				(= local12 2)
				(ego setCycle: Beg self)
				(aActor1 setMotion: MoveTo 155 34)
				(aBigEgo setCycle: Beg)
				(= state 11)
			)
			(16)
			(17
				(User mapKeyToDir: 1 canInput: 0 canControl: 0)
				(aBigEgo posn: 1234 1234 setMotion: 0)
				(aActor1 posn: 155 34 setMotion: 0 stopUpd:)
				(aRoundBar show:)
				(aDumbbell show:)
				(ego
					posn: 216 163
					setLoop: 3
					setCel: 0
					setCycle: End self
				)
				(theGame setCursor: 998 (HaveMouse))
			)
			(18 (localproc_00c8))
			(19
				(theGame setCursor: 998 (HaveMouse))
				(ego
					illegalBits: 0
					viewer: 0
					setPri: 13
					setMotion: MoveTo 156 171 self
				)
			)
			(20
				(ego
					view: 381
					posn: 156 146
					setLoop: 5
					cel: 0
					setCycle: End self
				)
			)
			(21
				(localproc_001a 2)
				(ego
					posn: 165 143
					setPri: 13
					setLoop: 6
					cel: 0
					cycleSpeed: 2
				)
				(aBigEgo view: 384 setLoop: 0 posn: 302 1)
				(self cue:)
			)
			(22 (= local2 1) (= local12 3))
			(23
				(= local12 1)
				(ego setCycle: End)
				(aBigEgo setMotion: MoveTo 302 -52 self)
			)
			(24
				(= local12 4)
				(if local2
					(if (== (++ global116) pullUps)
						(Printf 380 19 global116)
						(RoomScript changeState: 27)
					)
					(localproc_013c)
				)
			)
			(25
				(= local12 2)
				(ego setCycle: Beg)
				(aBigEgo setMotion: MoveTo 302 1 self)
				(= state 21)
			)
			(26)
			(27
				(theGame setCursor: 998 (HaveMouse))
				(User mapKeyToDir: 1)
				(= oldEgoX 156)
				(= oldEgoY 175)
				(localproc_00c8)
			)
			(28
				(localproc_001a 1)
				(ego
					cycleSpeed: 1
					view: 381
					posn: 131 165
					setLoop: 1
					cel: 0
					setCycle: End self
					setPri: 13
				)
			)
			(29
				(ego setLoop: 4 setCel: 0 posn: 126 165)
				(aBigEgo
					cycleSpeed: 1
					view: 383
					posn: 76 115
					setPri: 13
					cel: 0
				)
				(aActor1
					setStep: 3 5
					posn: 155 45
					view: 384
					setLoop: 2
					setPri: 12
				)
				(aExtraBar hide:)
				(aBarPullBarView hide:)
				(self cue:)
			)
			(30 (= local2 1) (= local12 4))
			(31
				(= local12 2)
				(ego setCycle: End)
				(aBigEgo setCycle: End)
				(aActor1 setMotion: MoveTo 155 15 self)
				(aCenterWeight setMotion: MoveTo 153 136)
			)
			(32
				(= local12 3)
				(if local2
					(if (== (++ global117) barPulls)
						(Printf 380 20 global117)
						(RoomScript changeState: 35)
					)
					(localproc_013c)
				)
			)
			(33
				(= local12 1)
				(ego setCycle: Beg)
				(aBigEgo setCycle: Beg)
				(aActor1 setMotion: MoveTo 155 50 self)
				(aCenterWeight setMotion: MoveTo 153 166)
				(= state 29)
			)
			(34)
			(35
				(User mapKeyToDir: 1 canInput: 0 canControl: 0)
				(aCenterWeight setMotion: MoveTo 153 166 self)
				(aActor1 setMotion: MoveTo 155 50 self)
				(aBarPullBarView show: stopUpd:)
				(aExtraBar show: stopUpd:)
				(aBigEgo posn: 999 999)
				(ego posn: 131 165 setLoop: 3 cel: 0 setCycle: End self)
			)
			(36)
			(37)
			(38
				(aCenterWeight stopUpd:)
				(localproc_00c8)
			)
			(39
				(= currentEgo 20)
				(= local3 1)
				(= global66 720)
				(NormalEgo 2 724)
				(HandsOff)
				(= seconds 3)
			)
			(40
				(Print 380 21)
				(gTheMusic number: 380 loop: global72 play:)
				(= local1 0)
				(NormalEgo 2 704)
				(HandsOff)
				(= currentStatus (theGame setSpeed: 6))
				(= seconds 3)
			)
			(41
				(ego
					illegalBits: 64
					ignoreActors:
					view: 385
					setLoop: local1
					cel: 0
					setCycle: End self
				)
			)
			(42
				(cond 
					((== 0 local1) (Print 380 22))
					((== 3 local1) (Print 380 23))
				)
				(if (>= 4 (++ local1)) (= state (- state 2)))
				(= cycles 11)
			)
			(43
				(Print 380 24)
				(ego view: 386 loop: 0 cel: 0 setCycle: End)
				(= cycles 18)
			)
			(44
				(ego loop: 1 cel: 0 setCycle: Fwd)
				(= cycles 33)
			)
			(45
				(Print 380 25)
				(= cycles 33)
			)
			(46
				(ego loop: 2 cel: 0 setCycle: End)
				(= cycles 11)
			)
			(47
				(Print 380 26)
				(ego setCycle: Beg)
				(= cycles 11)
			)
			(48
				(ego loop: 3 cel: 0 setCycle: End)
				(= cycles 15)
			)
			(49
				(ego setCycle: Beg)
				(= cycles 11)
			)
			(50
				(Print 380 27)
				(ego loop: 5 cel: 0 setCycle: End)
				(= cycles 22)
			)
			(51
				(ego setCycle: Beg)
				(= cycles 11)
			)
			(52
				(ego loop: 4 cel: 0 setCycle: End)
				(= cycles 15)
			)
			(53
				(ego setCycle: Beg)
				(= cycles 11)
			)
			(54
				(ego loop: 6 cel: 0 setCycle: End)
				(= cycles 22)
			)
			(55
				(ego setCycle: Beg)
				(= cycles 11)
			)
			(56
				(ego view: 387 loop: 0 cel: 0 setCycle: End self)
			)
			(57
				(Print 380 28)
				(ego loop: 1 setCycle: Fwd)
				(= cycles 33)
			)
			(58
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(59
				(NormalEgo 0 724)
				(ego viewer: egoViewer)
				(theGame changeScore: 100)
				(Print 380 29)
				(gTheMusic number: 399 loop: global72 play:)
				(theGame setSpeed: currentStatus)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(and currentActivity (== (event type?) evMOUSEBUTTON))
			(event claimed: 1)
			(cond 
				((& (event modifiers?) emSHIFT)
					(cond 
						((== local12 3) (self cue:))
						((== local12 2) (++ state) (self cue:))
					)
				)
				((== local12 4) (self cue:))
				((== local12 1) (++ state) (self cue:))
			)
		)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(cond 
			(
				(or
					(Said 'nightstand,(nightstand<up),(get<off,up)')
					(Said '(get<off,up),exit,cease,done')
					(Said 'exit/barstool')
				)
				(switch currentActivity
					(0 (YouAre))
					(4 (RoomScript changeState: 7))
					(3 (RoomScript changeState: 17))
					(2 (RoomScript changeState: 27))
					(1 (RoomScript changeState: 35))
				)
			)
			((Said 'lie,(work<out),use')
				(cond 
					(currentActivity (Print 380 0))
					((& (ego onControl:) $0008)
						(if (>= global115 legCurls)
							(Print 380 1)
						else
							(self changeState: 1)
						)
					)
					((& (ego onControl:) $0004)
						(if (>= global114 poundsPumped)
							(Print 380 2)
						else
							(self changeState: 10)
						)
					)
					((& (ego onControl:) $0010)
						(if (>= global116 pullUps)
							(Print 380 3)
						else
							(self changeState: 19)
						)
					)
					((& (ego onControl:) $0020)
						(if (>= global117 barPulls)
							(Print 380 4)
						else
							(self changeState: 28)
						)
					)
					(else (Print 380 5) (Print 380 6))
				)
			)
			(
				(or
					(Said 'use/equipment<drag,bar')
					(Said 'use/bar,drag')
					(Said 'use/drag<bar')
					(Said 'drag/bar')
				)
				(cond 
					(currentActivity (Print 380 0))
					((>= global117 barPulls) (Print 380 7))
					((& (ego onControl:) $0020) (self changeState: 28))
					(else (Print 380 8))
				)
			)
			(
				(or
					(Said 'use/equipment<curl')
					(Said 'curl')
					(Said 'use/curl')
				)
				(cond 
					(currentActivity (Print 380 0))
					((>= global115 legCurls) (Print 380 9))
					((& (ego onControl:) $0008) (self changeState: 1))
					(else (Print 380 10))
				)
			)
			(
				(or
					(Said 'use/equipment<drag,barstool')
					(Said 'drag,bell')
					(Said 'barstool<drag/')
					(Said 'get<on/barstool')
					(Said 'increase,use/drag,bell,bell')
				)
				(cond 
					(currentActivity (Print 380 0))
					((>= global114 poundsPumped) (Print 380 11))
					((& (ego onControl:) $0004) (self changeState: 10))
					(else (Print 380 12))
				)
			)
			(
				(or
					(Said 'use/equipment<up,pullup,drag')
					(Said 'use/up<drag')
					(Said 'pullup')
					(Said '(up<drag)')
					(Said 'use/pullup')
				)
				(cond 
					(currentActivity (Print 380 0))
					((>= global116 pullUps) (Print 380 13))
					((& (ego onControl:) $0010) (self changeState: 19))
					(else (Print 380 14))
				)
			)
			((Said 'perspiration') (if currentActivity (YouAre) else (Print 380 15)))
			(
			(and (Said 'look>') (Said '[/equipment,pos,area]')) (Print 380 16))
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 106)
						(< (event x?) 128)
						(> (event y?) 146)
						(< (event y?) 172)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								(currentActivity (Print 380 0))
								((>= global117 barPulls) (Print 380 7))
								((& (ego onControl:) $0020) (self changeState: 28))
								(else (Print 380 8))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 142)
						(< (event x?) 166)
						(> (event y?) 150)
						(< (event y?) 172)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								(currentActivity (Print 380 0))
								((>= global116 pullUps) (Print 380 13))
								((& (ego onControl:) $0010) (self changeState: 19))
								(else (Print 380 14))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 167)
						(< (event x?) 202)
						(> (event y?) 131)
						(< (event y?) 168)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								(currentActivity (Print 380 0))
								((>= global114 poundsPumped) (Print 380 11))
								((& (ego onControl:) $0004) (self changeState: 10))
								(else (Print 380 12))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 203)
						(< (event x?) 230)
						(> (event y?) 150)
						(< (event y?) 170)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								(currentActivity (Print 380 0))
								((>= global115 legCurls) (Print 380 9))
								((& (ego onControl:) $0008) (self changeState: 1))
								(else (Print 380 10))
							)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance atpPullupHandles of PV
	(properties
		y 143
		x 165
		view 380
		loop 2
		priority 9
		signal $4000
	)
)

(instance aRoundBar of View
	(properties
		y 142
		x 201
		view 381
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 stopUpd:)
	)
)

(instance aBarPullBarView of View
	(properties
		y 63
		x 30
		view 380
		loop 1
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 stopUpd:)
	)
)

(instance aLegCurlBar of View
	(properties
		y 164
		x 226
		view 381
		cel 2
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance aDumbbell of View
	(properties
		y 34
		x 155
		view 380
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 stopUpd:)
	)
)

(instance aExtraBar of View
	(properties
		y 138
		x 128
		view 381
		cel 1
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance aActor1 of Act
	(properties
		y 999
		x 155
		view 380
		signal $4000
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreHorizon: stopUpd:)
	)
)

(instance aCenterWeight of Act
	(properties
		y 166
		x 153
		view 381
		signal $4000
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setStep: 1 5 setLoop: 7 setPri: 11 stopUpd:)
	)
)

(instance aBigEgo of Act
	(properties
		y 999
		x 156
		view 382
		signal $4000
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 setStep: 6 6 ignoreHorizon: stopUpd:)
	)
)

(instance egoViewer of Code
	(properties)
	
	(method (doit)
		(cond 
			((< (ego y?) 172) (ego setPri: 4))
			((& (ego onControl:) $0002) (ego setPri: 9))
			(else (ego setPri: -1))
		)
	)
)

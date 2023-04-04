;;; Sierra Script 1.0 - (do not remove this comment)
(script# 530)
(include sci.sh)
(use Main)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm530 0
	RoomScript 1
)
(synonyms
	(palm bureau)
	(blade dope)
)

(local
	local0
	onOtherSide
	local2
	ropeState
	[msgBuf 44]
	[titleBuf 23]
	local71
)
(instance rm530 of Rm
	(properties
		picture 530
		horizon 1
	)
	
	(method (init)
		(Load rsVIEW 813)
		(Load rsSOUND 4)
		(Load rsSCRIPT 991)
		(cls)
		(super init:)
		(self setScript: RoomScript)
		(aRope init:)
		(User canInput: 0 mapKeyToDir: 0)
		(if (!= prevRoomNum 525)
			(gTheMusic number: 599 loop: global72 play:)
		)
		(cond 
			((== prevRoomNum 535)
				(= musicLoop 1)
				(= onOtherSide 1)
				(= ropeState 531)
				(= global66 802)
				(NormalEgo 2)
				(ego setStep: 2 1 posn: 215 75 init:)
				(aRope posn: 227 72 setCycle: End RopeScript)
			)
			((== prevRoomNum 540)
				(= musicLoop 1)
				(= onOtherSide 1)
				(= ropeState 531)
				(= global66 802)
				(NormalEgo 2)
				(ego setStep: 2 1 posn: 31 78 init:)
				(aRope posn: 227 72 setCel: 255 stopUpd:)
			)
			(else
				(= musicLoop 1)
				(= global66 800)
				(Load rsVIEW 531)
				(Load rsVIEW 532)
				(Load rsVIEW 533)
				(Load rsVIEW 534)
				(Load rsVIEW 804)
				(Load rsVIEW 20)
				(Load rsVIEW 27)
				(Load rsSOUND 531)
				(Load rsSOUND 530)
				(Load rsSOUND 12)
				(Load rsSOUND 599)
				(gTheMusic number: 4 loop: 1 play:)
				(= gCurRoomNum 530)
				(ego
					ignoreActors:
					ignoreHorizon:
					view: 804
					setLoop: 0
					setCycle: Fwd
					setStep: 1 8
					setPri: 12
					illegalBits: 0
					posn: 170 -99
					init:
				)
				(RoomScript changeState: 1)
			)
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(== (ego view?) 534)
					(== (ego loop?) 1)
					(== (ego cel?) 1)
				)
				(orchidSeconds number: 530 loop: 1 play:)
			)
			(
			(and (& (ego onControl:) $0002) (== gCurRoomNum 0))
				(= gCurRoomNum 2)
				(ego posn: (- (ego x?) 25) (ego y?))
				(self changeState: 65)
			)
			(
			(and (& (ego onControl:) $0080) (== gCurRoomNum 0))
				(= gCurRoomNum 2)
				(ego posn: (+ (ego x?) 25) (ego y?))
				(self changeState: 65)
			)
			(
			(and (& (ego onControl:) $0040) (== gCurRoomNum 0))
				(= gCurRoomNum 2)
				(ego posn: (ego x?) (+ (ego y?) 5))
				(self changeState: 65)
			)
			((& (ego onControl:) $0400) (curRoom newRoom: 540))
			(
				(and
					(== ropeState 3)
					(or (!= 142 (ego x?)) (!= 128 (ego y?)))
				)
				(= ropeState 531)
				(self changeState: 58)
			)
			(
				(and
					(== gCurRoomNum 0)
					(not (Btst 6))
					(not onOtherSide)
				)
				(cond 
					((== global71 30) (++ global71) (Print 530 0))
					((== global71 60) (++ global71) (Print 530 1))
					((or (> global71 90) (== ropeState 4)) (self changeState: 12))
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff))
			(1
				(ego
					ignoreActors:
					setStep: 1 1
					setMotion: JumpTo 170 155 self
				)
			)
			(2
				(ego setLoop: 1 setCel: 0)
				(gTheMusic number: 12 loop: 1 play:)
				(ShakeScreen 3 1)
				(= seconds 4)
			)
			(3
				(Print 530 64)
				(= seconds 4)
			)
			(4
				(Print 530 65)
				(= seconds 4)
			)
			(5
				(ego setCycle: End self)
				(addToPics add: atpTits doit:)
			)
			(6
				(gTheMusic number: 599 loop: global72 play:)
				(ego posn: 170 155 cel: 1)
				(NormalEgo 2)
				(= gCurRoomNum 0)
				(theGame setSpeed: currentStatus)
			)
			(7
				(if (not (Btst 58))
					(Bset 58)
					(theGame changeScore: 10)
				)
				(Ok)
				(Print 530 66)
				(HandsOff)
				(= local2 0)
				(ego
					view: 531
					cycleSpeed: 1
					setStep: 2 1
					setLoop: 0
					cel: 0
					setCycle: End self
				)
			)
			(8
				(ego
					cycleSpeed: 0
					setLoop: 1
					setCycle: Fwd
					setMotion: MoveTo (Random 111 195) (ego y?) self
				)
			)
			(9
				(= cycles 11)
				(if (> 4 (++ local2)) (= state 7))
			)
			(10
				(ego
					cycleSpeed: 1
					setLoop: 0
					setCel: 255
					setCycle: Beg self
				)
			)
			(11
				(NormalEgo)
				(= ropeState 1)
				(ego loop: 2 get: 20)
				(Print 530 67)
			)
			(12
				(HandsOff)
				(= gCurRoomNum 1001)
				(Print 530 68)
				(ego loop: 2)
				(gTheMusic fade:)
				(= seconds 3)
			)
			(13
				(ego view: 804 loop: 1 setCel: 255 setCycle: Beg self)
			)
			(14
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 537
					register: (Format @msgBuf 530 69)
					next: (Format @titleBuf 530 70)
				)
			)
			(15
				(Ok)
				(HandsOff)
				(ego setMotion: MoveTo 151 142 self)
				(gTheMusic fade:)
			)
			(16
				(ego view: 531 loop: 8 cel: 0 setCycle: End self)
				(= seconds 3)
			)
			(17
				(theGame changeScore: -50)
				(if (== gCurRoomNum 535)
					(Print 530 71 #at -1 10 #icon 20 0 0)
					(= state 24)
				else
					(Print 530 72 #at -1 10 #icon 20 0 0)
				)
				(= seconds 3)
			)
			(18
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(19
				(Print 530 73 #dispose #at -1 10)
				(= local2 0)
				(ego loop: 3 cel: 0 setCycle: End self)
			)
			(20
				(ego loop: 4 cel: 0 setCycle: End self)
			)
			(21
				(if (> 3 (++ local2)) (= state 19))
				(= cycles 20)
			)
			(22
				(ego loop: 5 cel: 0 setCycle: Fwd)
				(= cycles (* 3 (NumCels ego)))
			)
			(23
				(ego loop: 6 cel: 0 setCycle: End self)
			)
			(24
				(ego loop: 7 cel: 0 setCycle: End)
				(= cycles 44)
			)
			(25
				(Print 530 74 #dispose #at -1 10)
				(gTheMusic number: 531 loop: -1 play:)
				(= seconds 3)
			)
			(26
				(theGame setSpeed: 6)
				(ego
					view: 533
					posn: (ego x?) (- (ego y?) 26)
					cycleSpeed: 1
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(27
				(ego
					put: 20 -1
					setLoop: 1
					cel: 0
					illegalBits: 0
					ignoreActors:
					setPri:
					setStep: 1 1
					setCycle: Fwd
					setMotion: MoveTo 232 91 self
				)
				(= cycles 11)
			)
			(28
				(Print 530 75 #dispose #at -1 10)
				(= cycles 11)
			)
			(29
				(Print 530 76 #dispose #at -1 10)
			)
			(30
				(Print 530 77 #dispose #at -1 10)
				(ego setPri: 2 setMotion: MoveTo 49 91 self)
				(= cycles 22)
			)
			(31
				(Print 530 78 #dispose #at -1 10)
				(= cycles 22)
			)
			(32
				(Print 530 79 #dispose #at -1 144)
			)
			(33
				(gTheMusic fade:)
				(Print 530 80 #dispose #at -1 10)
				(= seconds 3)
			)
			(34
				(= seconds (= cycles 0))
				(Print
					(Format @msgBuf 530 81 filthStr)
					#dispose
					#at
					-1
					10
				)
				(gTheMusic number: 4 loop: 1 play:)
				(ego setLoop: 2 cel: 0 cycleSpeed: 0 setCycle: End self)
				(= state 65)
			)
			(35
				(Ok)
				(HandsOff)
				(Print 530 82)
				(if (>= global88 3) (Print 530 83 #at -1 144))
				(= gCurRoomNum 533)
				(ego
					view: 532
					setLoop: 0
					illegalBits: 0
					posn: 129 116
					setPri: 11
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 128 47 self
				)
			)
			(36
				(= gCurRoomNum 536)
				(User canInput: 1)
			)
			(37
				(Ok)
				(ego setLoop: 1 cel: 0 setCycle: End self)
			)
			(38
				(ego setLoop: 0 cel: 0 setCycle: End self)
			)
			(39
				(ego setLoop: 2 cel: 0 setCycle: End self)
			)
			(40
				(ego get: 19 setLoop: 0 cel: 0 setCycle: End self)
			)
			(41
				(theGame changeScore: 25)
				(Print 530 84)
				(if (>= global88 3) (Print 530 85 #at -1 144))
				(User canInput: 1)
			)
			(42
				(Ok)
				(HandsOff)
				(ego
					cycleSpeed: 1
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 129 116 self
				)
			)
			(43
				(ego posn: 128 132)
				(NormalEgo 3)
				(= gCurRoomNum 0)
			)
			(44
				(Ok)
				(HandsOff)
				((Inv at: 20) view: 27)
				(Format ((Inv at: 20) name?) 530 86)
				(theGame changeScore: 100)
				(Print 530 87 #at -1 10 #icon 20 0 0)
				(= seconds 3)
			)
			(45
				(Print 530 88)
				(= ropeState 2)
				(NormalEgo)
			)
			(46
				(HandsOff)
				(Ok)
				(= gCurRoomNum 531)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 141 129 self
				)
			)
			(47
				(gTheMusic stop:)
				(ego
					view: 534
					posn: 142 128
					setLoop: 1
					cel: 0
					setCycle: Fwd
				)
				(= cycles (* 3 (- (NumCels ego) 1)))
			)
			(48
				(ego setLoop: 0 cel: 0 setCycle: End self)
			)
			(49
				(if (> 2 (++ local0)) (= state 46))
				(= seconds 3)
			)
			(50
				(Print 530 89 #at -1 10)
				(ego setLoop: 1 setCycle: Fwd)
				(= cycles (* 3 (- (NumCels ego) 1)))
			)
			(51
				(ego setLoop: 2 cel: 0 setCycle: End self)
			)
			(52
				(theGame changeScore: 20)
				(Print 530 90 #at 10 5 #width 290)
				(= ropeState 3)
				(orchidSeconds stop:)
				(gTheMusic play:)
				(HandsOn)
			)
			(53
				(= ropeState 4)
				(theGame changeScore: 20)
				(Ok)
				(HandsOff)
				(ego
					view: 534
					posn: 141 129
					setPri: 9
					setLoop: 3
					setCycle: Fwd
				)
				(aRope
					view: 530
					setLoop: 0
					cel: 0
					posn: 227 72
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(54
				(aRope stopUpd:)
				(= cycles 10)
			)
			(55
				(Print 530 91 #at 10 5 #width 290)
				(NormalEgo 1)
				(ego put: 20 -1)
				(= gCurRoomNum 0)
			)
			(56
				(HandsOff)
				(Ok)
				(ego view: 534 setLoop: 4 cel: 0 setCycle: End self)
			)
			(57
				(theGame changeScore: 50)
				(Print 530 92 #at 10 5 #width 290)
				(= global66 803)
				((Inv at: 17) view: 31)
				(NormalEgo 2)
			)
			(58
				(HandsOff)
				(ego posn: 141 129)
				(NormalEgo 0)
				(aRope
					view: 530
					posn: 227 72
					cycleSpeed: 1
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(59
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 27
					register: (Format @msgBuf 530 93)
					next: (Format @titleBuf 530 94)
				)
			)
			(60
				(Ok)
				(HandsOff)
				(= gCurRoomNum 532)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 141 129 self
				)
			)
			(61
				(if (!= global66 803)
					(self cue:)
				else
					(Print 530 95 #at -1 10)
					(ego
						view: 534
						setLoop: 5
						cel: 0
						cycleSpeed: 1
						setCycle: End self
					)
				)
			)
			(62
				(ego
					view: 534
					setLoop: 6
					cel: 0
					posn: 149 107
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(63
				(Print 530 96)
				(gTheMusic fade:)
				(= seconds 3)
			)
			(64 (curRoom newRoom: 535))
			(65
				(HandsOff)
				(Print
					(Format @msgBuf 530 81 filthStr)
					#at
					-1
					10
					#dispose
				)
				(gTheMusic number: 4 loop: 1 play:)
				(ego
					view: 813
					setLoop: (if onOtherSide 1 else 0)
					illegalBits: 0
					ignoreActors:
					setPri: 5
					setStep: 1 8
					cel: 0
					cycleSpeed: 0
					setCycle: End self
				)
			)
			(66
				(ego setMotion: theJump)
				(theJump y: 300)
			)
			(67
				(cls)
				(if (or (== gCurRoomNum 534) (== gCurRoomNum 535))
					(Print 530 97)
				else
					(Print 530 98)
				)
				(if global64
					(NormalEgo)
					(= gCurRoomNum 0)
					(if onOtherSide
						(ego posn: 210 77 setStep: 2 1)
					else
						(ego posn: 159 158)
					)
				else
					(theGame setScript: (ScriptID 40))
					((ScriptID 40)
						caller: 814
						register: (Format @msgBuf 530 99)
					)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(cond 
			((Said 'make,weave/blade,hemp')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					(onOtherSide (Print 530 2))
					(else
						(switch ropeState
							(0 (Print 530 3))
							(1 (self changeState: 44))
							(else  (Print 530 4))
						)
					)
				)
			)
			((and (>= ropeState 4) (Said 'unfasten')) (Print 530 5))
			(
			(or (Said 'hemp/boulder') (Said 'throw/blade,hemp'))
				(if (!= gCurRoomNum 0)
					(GoodIdea)
				else
					(switch ropeState
						(0 (Print 530 6))
						(1
							(Print 530 7)
							(ego put: 20 curRoomNum)
							(= ropeState 0)
						)
						(2
							(if (not (& (ego onControl:) $0004))
								(Print 530 8)
							else
								(self changeState: 46)
							)
						)
						(3 (ItIs))
						(4 (ItIs))
						(531 (Print 530 9))
					)
				)
			)
			((Said 'attach/hemp>')
				(cond 
					((Said '//coconut') (Print 530 10))
					((!= gCurRoomNum 531) (event claimed: 1) (Print 530 11))
					((Said '/[/!*]') (Print 530 12))
					((Said '//palm')
						(switch ropeState
							(2 (Print 530 11))
							(3 (self changeState: 53))
							(4 (ItIs))
							(531 (Print 530 13))
							(else  (Print 530 14))
						)
					)
				)
			)
			(
				(or
					(Said 'make/belt,throw,belt,barstool,belt')
					(Said 'attach/hemp/i,self,entertainer')
					(Said 'use/dress')
					(Said 'break/skirt,cloth,dress')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((== global66 803) (Print 530 15))
					((< ropeState 3) (Print 530 16))
					((< ropeState 4) (Print 530 17))
					((> ropeState 4) (Print 530 18))
					(else (self changeState: 56))
				)
			)
			(
				(or
					(Said 'cross/canyon,hemp')
					(Said 'cross//canyon,hemp')
					(Said 'bang,go,grab,exit,use,climb/hemp,belt')
					(Said 'bang,go,grab,exit,use,climb//hemp,belt')
				)
				(cond 
					((!= gCurRoomNum 0) (Print 530 19))
					(onOtherSide (Print 530 20))
					((< ropeState 4) (Print 530 21))
					((> 129 (ego y?)) (NotClose))
					(else (self changeState: 60))
				)
			)
			((and onOtherSide (Said '/hemp')) (Print 530 22))
			((and ropeState (Said 'test,look/hemp,knot')) (Print 530 23))
			(
			(and (== ropeState 3) (Said 'use,climb,throw/hemp')) (Print 530 24))
			((Said 'climb/palm')
				(cond 
					((== gCurRoomNum 536) (self changeState: 42))
					((!= gCurRoomNum 0) (GoodIdea))
					(onOtherSide (Print 530 25))
					((& (ego onControl:) $0020) (Print 530 26))
					((not (& (ego onControl:) $0010)) (Print 530 27))
					(else (self changeState: 35))
				)
			)
			(
			(and (== gCurRoomNum 536) (Said 'go,climb<down')) (self changeState: 42))
			((Said 'pick,get/coconut')
				(cond 
					((ego has: 19) (Print 530 28))
					((!= gCurRoomNum 536) (Print 530 29))
					(else (self changeState: 37))
				)
			)
			((Said 'climb[<down]/boulder,canyon,ledge') (Print 530 30) (Print 530 31 #at -1 144))
			((Said 'climb<up[/boulder,canyon,ledge]') (Print 530 32))
			(
			(or (Said 'look<down') (Said 'look/cliff,edge,canyon')) (Print 530 33))
			(
				(and
					(not onOtherSide)
					(or
						(Said 'look/air,hose')
						(Said 'climb/cliff,cliff,cliff')
						(Said 'look<up')
					)
				)
				(Print 530 34)
				(Print 530 35)
			)
			((Said 'pick,get/blade,bush,bush,hemp')
				(if (!= gCurRoomNum 0)
					(GoodIdea)
				else
					(switch ropeState
						(0
							(if (not (& (ego onControl:) $0008))
								(NotClose)
							else
								(self changeState: 7)
							)
						)
						(1 (Print 530 36))
						(2 (Print 530 37))
						(else  (Print 530 38))
					)
				)
			)
			(
				(or
					(Said 'make,drag/blade')
					(Said 'burn,smoke/bush,bush,blade')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (ego has: 20)) (Print 530 39))
					((> ropeState 1) (Print 530 40))
					(else (= gCurRoomNum 534) (self changeState: 15))
				)
			)
			(
				(or
					(Said 'backdrop/blade,bush/lip')
					(Said 'eat,eat/bush,bush,blade')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (ego has: 20)) (Print 530 41))
					((> ropeState 1) (Print 530 42))
					(else (= gCurRoomNum 535) (self changeState: 15))
				)
			)
			((Said 'unfasten') (Print 530 43))
			((Said 'drag,drag,drag/palm') (Print 530 44))
			(
				(and
					(ego has: 19)
					(or
						(Said 'use,attach/coconut')
						(Said 'use,attach//coconut')
					)
				)
				(Print 530 45)
			)
			((Said 'look>')
				(cond 
					((Said '/boulder')
						(cond 
							((== ropeState 531) (Print 530 22))
							((== ropeState 4) (Print 530 46) (Print 530 47 #at -1 144))
							(else (Print 530 48))
						)
					)
					((Said '/carpet') (Print 530 49))
					((Said '/palm')
						(cond 
							(onOtherSide (Print 530 50))
							((== gCurRoomNum 536) (Print 530 51))
							(else (Print 530 52))
						)
					)
					((Said '/coconut')
						(if (>= global88 2) (Print 530 53 #at -1 144))
						(cond 
							((ego has: 19) (event claimed: 0))
							(onOtherSide (Print 530 54))
							(else (Print 530 55) (Print 530 56))
						)
					)
					(
						(and
							(not (ego has: 20))
							(or (Said '/blade') (Said '/bush,exit'))
						)
						(if (== gCurRoomNum 536)
							(Print 530 57)
						else
							(Print 530 58)
						)
					)
					((Said '/bush,exit')
						(cond 
							((== gCurRoomNum 536) (Print 530 57))
							(onOtherSide (Print 530 59))
							(else (Print 530 60))
						)
					)
					((Said '[/ledge,area]')
						(cond 
							((== gCurRoomNum 536) (Print 530 61))
							(onOtherSide (Print 530 62))
							(else (Print 530 63))
						)
					)
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if (== theCursor 997) (event claimed: 1))
				(if (== theCursor 992) (event claimed: 1))
				(if (== gCurRoomNum 536)
					(event claimed: 1)
					(if (== theCursor 992)
						(event claimed: 1)
						(= local71
							(Print
								{What do you want to do now?}
								#button
								{Climb down}
								1
								#button
								{Take Coconut}
								2
							)
						)
						(switch local71
							(1
								(event claimed: 1)
								(self changeState: 42)
								(event claimed: 1)
							)
							(2
								(event claimed: 1)
								(cond 
									((ego has: 19) (Print 530 28))
									((!= gCurRoomNum 536) (Print 530 29))
									(else
										(event claimed: 1)
										(self changeState: 37)
										(event claimed: 1)
									)
								)
							)
						)
					)
				)
				(if (proc0_27 38 237 166 189 event)
					(switch theCursor
						(992 (event claimed: 1))
						(998
							(event claimed: 1)
							(Print {You look marijuana plants})
						)
						(995
							(event claimed: 1)
							(if (!= gCurRoomNum 0)
								(GoodIdea)
							else
								(switch ropeState
									(0
										(if (not (& (ego onControl:) $0008))
											(NotClose)
										else
											(self changeState: 7)
										)
									)
									(1 (Print 530 36))
									(2 (Print 530 37))
									(else  (Print 530 38))
								)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 ego (event x?) (event y?))
					(if (== theCursor 20)
						(event claimed: 1)
						(= temp0
							(Print
								{What do you want to do?}
								#button
								{Smoke}
								1
								#button
								{A rope}
								2
								#button
								{Eat}
								3
							)
						)
						(switch temp0
							(1
								(cond 
									((!= gCurRoomNum 0) (GoodIdea))
									((not (ego has: 20)) (Print 530 39))
									((> ropeState 1) (Print 530 40))
									(else (= gCurRoomNum 534) (self changeState: 15))
								)
							)
							(2
								(cond 
									((!= gCurRoomNum 0) (GoodIdea))
									(onOtherSide (Print 530 2))
									(else
										(switch ropeState
											(0 (Print 530 3))
											(1 (self changeState: 44))
											(else  (Print 530 4))
										)
									)
								)
							)
							(3
								(cond 
									((!= gCurRoomNum 0) (GoodIdea))
									((not (ego has: 20)) (Print 530 41))
									((> ropeState 1) (Print 530 42))
									(else (= gCurRoomNum 535) (self changeState: 15))
								)
							)
							(else  (event claimed: 0))
						)
					)
					(if
						(or
							(== theCursor 17)
							(== theCursor 994)
							(== theCursor 995)
						)
						(event claimed: 1)
						(cond 
							((!= gCurRoomNum 0) (GoodIdea))
							((== global66 803) (Print 530 15))
							((< ropeState 3) (Print 530 16))
							((< ropeState 4) (Print 530 17))
							((> ropeState 4) (Print 530 18))
							(else (self changeState: 56))
						)
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
								(else ((ScriptID 540 1) changeState: 1))
							)
						)
					)
				)
				(if
				(and (proc0_27 135 250 69 117 event) (== ropeState 4))
					(if (== theCursor 992) (event claimed: 1))
					(if (== theCursor 995)
						(event claimed: 1)
						(cond 
							((!= gCurRoomNum 0) (Print 530 19))
							(onOtherSide (Print 530 20))
							((< ropeState 4) (Print 530 21))
							((> 129 (ego y?)) (NotClose))
							(else (self changeState: 60))
						)
					)
				)
				(if (proc0_27 227 258 58 85 event)
					(event claimed: 1)
					(switch theCursor
						(992 (event claimed: 1))
						(998 (Print 530 48))
						(27
							(if (!= gCurRoomNum 0)
								(GoodIdea)
							else
								(switch ropeState
									(0 (Print 530 6))
									(1
										(Print 530 7)
										(ego put: 20 curRoomNum)
										(= ropeState 0)
									)
									(2
										(if (not (& (ego onControl:) $0004))
											(Print 530 8)
										else
											(self changeState: 46)
										)
									)
									(3 (ItIs))
									(4 (ItIs))
									(531 (Print 530 9))
								)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_27 68 153 21 124 event)
					(event claimed: 1)
					(switch theCursor
						(992 (event claimed: 1))
						(27
							(if (!= gCurRoomNum 531)
								(event claimed: 1)
								(Print 530 11)
							else
								(switch ropeState
									(2 (Print 530 11))
									(3 (self changeState: 53))
									(4 (ItIs))
									(531 (Print 530 13))
									(else  (Print 530 14))
								)
							)
						)
						(995
							(cond 
								((== gCurRoomNum 536) (self changeState: 42))
								((!= gCurRoomNum 0) (GoodIdea))
								(onOtherSide (Print 530 25))
								((& (ego onControl:) $0020) (Print 530 26))
								((not (& (ego onControl:) $0010)) (Print 530 26))
								(else (self changeState: 35))
							)
						)
						(998
							(cond 
								(onOtherSide (Print 530 50))
								((== gCurRoomNum 536) (Print 530 51))
								(else (Print 530 52))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_27 1 319 21 62 event)
					(event claimed: 1)
					(switch theCursor
						(992 (event claimed: 1))
						(998
							(cond 
								((== gCurRoomNum 536) (Print 530 61))
								(onOtherSide (Print 530 62))
								(else (Print 530 63))
							)
						)
					)
				)
			)
		)
	)
)

(instance aRope of Prop
	(properties
		y 1111
		x 999
		view 530
		loop 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 7)
	)
)

(instance RopeScript of Script
	(properties)
	
	(method (cue)
		(aRope stopUpd:)
		(Print 530 100 #at 10 5 #width 290)
	)
)

(instance atpTits of PV
	(properties
		y 157
		x 169
		view 532
		loop 3
		priority 7
		signal $4000
	)
)

(instance theJump of Jump
	(properties)
	
	(method (init)
		(super init: ego RoomScript)
		(self yStep: 2)
	)
)

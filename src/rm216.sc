;;; Sierra Script 1.0 - (do not remove this comment)
(script# 216)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm216 0
)
(synonyms
	(box lid)
)

(local
	talkCount
	mailboxOpen
	[str 222]
)
(procedure (localproc_0016 &tmp temp0)
	(Print
		@str
		#at
		10
		15
		#title
		{Kalalau says...}
		#width
		160
		#time
		(= temp0 (PrintDelay @str))
		#dispose
	)
	(return (+ 3 temp0))
)

(procedure (localproc_0049 &tmp temp0)
	(Print
		@str
		#at
		10
		123
		#title
		{You say...}
		#width
		160
		#time
		(= temp0 (PrintDelay @str))
		#dispose
	)
	(return (+ 3 temp0))
)

(instance rm216 of Rm
	(properties
		picture 216
		east 210
	)
	
	(method (init)
		(if (not (Btst 20)) (aKandBB init:))
		(Load rsVIEW 217)
		(Load rsSOUND 217)
		(Load rsSOUND 218)
		(super init:)
		(aMailBox init: stopUpd:)
		(self setScript: RoomScript)
		(if (and (Btst 20) (not (Btst 27)))
			(Load rsVIEW 219)
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(NormalEgo)
		(ego
			loop: 1
			posn: 318 166
			observeBlocks: blockFence1 blockFence2
			init:
		)
		(if (cast contains: aKandBB)
			(orchidSeconds number: 216 loop: -1 play:)
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(User canInput: 0 mapKeyToDir: 0)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst 20)) (= cycles 40))
			)
			(1
				(HandsOff)
				(ego loop: 3)
				(Format @str 216 32)
				(= seconds (localproc_0049))
				(= cycles 0)
			)
			(2
				(Format @str 216 33)
				(= seconds (localproc_0016))
			)
			(3
				(Print 216 34 #at -1 144 #dispose #time 11)
				(= seconds 14)
			)
			(4
				(Format @str 216 35)
				(= seconds (localproc_0049))
			)
			(5
				(Format @str 216 36)
				(= seconds (localproc_0016))
			)
			(6
				(Format @str 216 37)
				(= seconds (localproc_0049))
			)
			(7
				(Format @str 216 38)
				(= seconds (localproc_0016))
			)
			(8
				(Format @str 216 39)
				(= seconds (localproc_0049))
			)
			(9
				(Format @str 216 40)
				(= seconds (localproc_0016))
			)
			(10
				(Bset 20)
				(Print
					(Format
						@str
						216
						41
						(if (>= global88 3) { lesbian} else {})
					)
					#dispose
					#time
					28
				)
				(= seconds 31)
			)
			(11
				(HandsOn)
				(cls)
				(= seconds 0)
			)
			(12
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 118 120 self
				)
			)
			(13
				(ego loop: 3)
				(= seconds 2)
			)
			(14
				(ego
					view: 217
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
				(orchidSeconds number: 217 loop: 1 play:)
			)
			(15
				(aMailBox setCel: (if (InRoom 1) 1 else 2) stopUpd:)
				(ego setCycle: Beg self)
			)
			(16
				(NormalEgo 3)
				(= mailboxOpen 1)
				(if (cast contains: aKandBB)
					(orchidSeconds number: 216 loop: -1 play:)
				)
			)
			(18
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 118 120 self
				)
			)
			(19
				(ego loop: 3)
				(= seconds 2)
			)
			(20
				(ego
					view: 217
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(21
				(aMailBox setCel: 0 stopUpd:)
				(ego setCycle: Beg self)
				(orchidSeconds number: 218 loop: 1 play:)
			)
			(22
				(NormalEgo 3)
				(= mailboxOpen 0)
				(if (cast contains: aKandBB)
					(orchidSeconds number: 216 loop: -1 play:)
				)
			)
			(23
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 118 120 self
				)
			)
			(24
				(ego loop: 3)
				(= seconds 2)
			)
			(25
				(ego
					cycleSpeed: 1
					view: 217
					setLoop: 0
					cel: 0
					setCycle: End self
				)
			)
			(26
				(aMailBox setCel: 2 stopUpd:)
				(ego setLoop: 1 cel: 0 setCycle: End self)
			)
			(27 (= seconds 2))
			(28
				(Print 216 42 #at 10 5 #width 290)
				(= cycles 22)
			)
			(29
				(ego view: 217 setLoop: 2 cel: 0 setCycle: End self)
			)
			(30 (= cycles 20))
			(31
				(Print 216 43 #at 10 5 #width 290)
				(ego setCycle: Beg self)
			)
			(32
				(ego setLoop: 1 setCel: 255 setCycle: Beg self)
			)
			(33
				(ego get: 1)
				(theGame changeScore: 20)
				(Print 216 44 #icon 217 3 0 #at -1 10)
				(= seconds 3)
			)
			(34 (NormalEgo 3))
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(or
					(and
						(== (event message?) KEY_RETURN)
						(== (event type?) evKEYBOARD)
					)
					(== (event type?) evMOUSEBUTTON)
				)
			)
			(event claimed: 1)
			(cls)
			(self cue:)
		)
		(if (event claimed?) (return))
		(cond 
			((Said 'climb,jump/wall') (if musicLoop (Print 216 0) else (Print 216 1)))
			((Said '/club,club') (if musicLoop (Print 216 2) else (Print 216 3)))
			((Said 'get/box') (Print 216 4))
			((Said 'open/door') (Print 216 5))
			((Said 'pick,break/bolt,door,fence') (Print 216 6))
			((Said 'climb/door') (if musicLoop (Print 216 7) else (Print 216 8)))
			((Said 'unbolt/door') (Print 216 9))
			((Said 'look<in/box')
				(cond 
					(musicLoop (Print 216 10))
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0002)) (NotClose))
					((not mailboxOpen) (Print 216 11))
					((InRoom 1) (Print 216 12))
					(else (Print 216 13) (Print 216 14))
				)
			)
			((Said 'open/box')
				(cond 
					(musicLoop (Print 216 10))
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0002)) (NotClose))
					(mailboxOpen (ItIs))
					(else (self changeState: 12))
				)
			)
			((Said 'close/box')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0002)) (NotClose))
					((not mailboxOpen) (ItIs))
					(else (self changeState: 18))
				)
			)
			((Said 'get/card,letter,letter,envelope')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (InRoom 1)) (Print 216 15))
					((not (& (ego onControl:) $0002)) (NotClose))
					((not mailboxOpen) (Print 216 16))
					(else (self changeState: 23))
				)
			)
			((Said 'tickle/finial') (Print 216 17))
			((Said '/wall,fence>')
				(cond 
					((Said 'climb/') (Print 216 18))
					((Said 'look/') (if musicLoop (Print 216 19) else (Print 216 20)))
					(else (event claimed: 1) (Print 216 21))
				)
			)
			((Said 'look>')
				(cond 
					(
					(and (InRoom 1) (Said '/letter,letter,envelope,card')) (Print 216 22))
					((Said '/box')
						(if (& (ego onControl:) $0002)
							(Print 216 23)
						else
							(Print 216 24)
						)
					)
					((Said '/door') (Print 216 25))
					((Said '/finial') (Print 216 26) (Print 216 27 #at -1 144))
					((Said '/building') (if musicLoop (Print 216 28) else (Print 216 29)))
					((Said '[/area]')
						(if musicLoop
							(Print 216 28)
						else
							(Print 216 30)
							(Print 216 31 #at -1 144)
						)
					)
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 308)
						(> (event y?) 143)
						(< (event y?) 188)
						(== theCursor 999)
					)
					(ego setMotion: MoveTo 321 168)
					(event claimed: 1)
				)
				(if (proc0_26 aMailBox (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								(musicLoop (Print 216 10))
								((!= gCurRoomNum 0) (GoodIdea))
								((not (& (ego onControl:) $0002)) (ego setMotion: MoveTo 130 126) (self changeState: 12))
								(mailboxOpen (Print {It's opened}))
								(else (self changeState: 12))
							)
						)
						(998
							(if (InRoom 1)
								(cond 
									((!= gCurRoomNum 0) (GoodIdea))
									((not (InRoom 1)) (Print 216 15))
									((not (& (ego onControl:) $0002)) (NotClose))
									((not mailboxOpen) (Print 216 16))
									(else (self changeState: 23))
								)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 59)
						(< (event x?) 93)
						(> (event y?) 75)
						(< (event y?) 102)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 216 25))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 175)
						(< (event x?) 319)
						(> (event y?) 69)
						(< (event y?) 143)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(if musicLoop (Print 216 28) else (Print 216 29))
						)
						(else  (event claimed: 0))
					)
				)
			)
			(else 0)
		)
	)
)

(instance aKandBB of Prop
	(properties
		view 218
	)
	
	(method (init)
		(super init:)
		(self posn: 246 42 setScript: KandBBScript)
	)
)

(instance KandBBScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(aKandBB loop: 0 setCycle: Fwd)
				(= cycles (Random 20 40))
			)
			(2
				(aKandBB setCel: 3)
				(= cycles (Random 20 40))
			)
			(3
				(aKandBB loop: 1 setCycle: Fwd)
				(= cycles (Random 20 40))
			)
			(4
				(aKandBB setCel: 3)
				(= cycles (Random 20 40))
			)
			(5
				(if (== 1 (Random 1 4))
					(aKandBB loop: 2 setCycle: End)
					(= cycles (Random 80 140))
				else
					(self changeState: 1)
				)
			)
			(6
				(aKandBB setCycle: Beg)
				(self changeState: 1)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'address/babe,babe,exwife')
			(switch (++ talkCount)
				(1
					(Print 216 45)
					(Print 216 46)
				)
				(2
					(Print 216 47)
					(Print 216 46)
				)
				(3
					(Print 216 48)
					(Print 216 49)
					(Print 216 50)
				)
				(else 
					(Print 216 51)
					(Print 216 52)
					(Print 216 53)
				)
			)
		)
		(if (Said '/club,club') (Print 216 54))
		(if (Said 'look/babe,babe,exwife')
			(if (> global88 3) (Print 216 55) else (Print 216 56))
		)
	)
)

(instance blockFence2 of Blk
	(properties
		top 190
		bottom 333
		right 190
	)
)

(instance blockFence1 of Blk
	(properties
		top 200
		bottom 333
		right 333
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 240
		view 219
		cycleSpeed 1
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
		view 219
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
				(aCredit2 setCycle: Beg self)
			)
			(4
				(aCredit2 setLoop: 2 setCycle: End)
				(= cycles 22)
			)
			(5
				(Bset 27)
				(aCredit1 setCycle: Beg)
				(aCredit2 setCycle: Beg self)
			)
			(6
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)

(instance aMailBox of Prop
	(properties
		y 107
		x 134
		view 216
		signal $0001
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm220 0
)

(local
	local0
	[plotString 222]
)
(procedure (localproc_000c &tmp temp0)
	(Print
		@plotString
		#at
		10
		5
		#width
		290
		#time
		(= temp0 (PrintDelay @plotString))
		#dispose
	)
	(return (+ 3 temp0))
)

(instance rm220 of Rm
	(properties
		picture 220
		horizon 67
		north 310
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(if (and (Btst 21) (not (Btst 28)))
			(Load rsVIEW 220)
			(Load rsVIEW 221)
			(Load rsFONT 9)
			(Load rsSOUND 110)
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(cond 
			((== prevRoomNum 300) (ego posn: 6 175))
			((== prevRoomNum 210) (ego posn: 2 151))
			((== prevRoomNum 310) (ego posn: 316 70))
			((== prevRoomNum 230) (ego posn: 316 142))
			(else (ego posn: 316 182))
		)
		(NormalEgo)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(cond 
				((& (ego onControl:) $0002) (curRoom newRoom: 300))
				((& (ego onControl:) $0004) (curRoom newRoom: 210))
				((& (ego onControl:) $0008) (curRoom newRoom: 310))
				((& (ego onControl:) $0010) (curRoom newRoom: 230))
				((& (ego onControl:) $0020) (curRoom newRoom: 250))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(cond 
					((not (Btst 20))
						(HandsOff)
						(Print 220 2)
						(ego loop: 1)
						(Animate (cast elements?) 0)
						(curRoom newRoom: 210)
					)
					((not (Btst 21)) (HandsOff) (= cycles 25))
				)
			)
			(2
				(Format @plotString 220 3)
				(ego setMotion: MoveTo 8 153 self)
				(= seconds (localproc_000c))
			)
			(3)
			(4
				(Format @plotString 220 4)
				(= seconds (localproc_000c))
			)
			(5
				(Format @plotString 220 5)
				(= seconds (localproc_000c))
			)
			(6
				(Format @plotString 220 6)
				(= seconds (localproc_000c))
			)
			(7
				(Format @plotString 220 7)
				(= seconds (localproc_000c))
			)
			(8
				(Format @plotString 220 8)
				(= seconds (localproc_000c))
			)
			(9
				(Format @plotString 220 9)
				(ego setMotion: MoveTo 85 153 self)
				(= seconds (localproc_000c))
			)
			(10)
			(11
				(Format @plotString 220 10)
				(gTheMusic fade:)
				(= seconds (localproc_000c))
			)
			(12
				(Format @plotString 220 11)
				(= seconds (localproc_000c))
			)
			(13
				(aBooth init: setMotion: MoveTo 111 153 self)
				(if (> global87 39)
					(gTheMusic number: 110 loop: 1 play:)
				)
			)
			(14
				(ego loop: 2)
				(= cycles 22)
			)
			(15
				(ego loop: 3)
				(= cycles 22)
			)
			(16
				(ego setPri: 10 setMotion: MoveTo 107 153 self)
			)
			(17
				(if (<= global87 39)
					(gTheMusic number: 110 loop: 1 play:)
				)
				(ego view: 221 loop: 1 cel: 0 setCycle: End self)
			)
			(18
				(ego
					loop: 2
					cel: 0
					posn: (ego x?) (- (ego y?) 16)
					setCycle: End self
				)
			)
			(19 (= cycles 22))
			(20
				(ego view: 221 loop: 3 cel: 0 setCycle: End self)
			)
			(21
				(Print 220 12 #at -1 10 #font 9 #time 3 #dispose)
				(ShakeScreen 7 3)
				(= seconds 5)
			)
			(22
				(= global66 700)
				(NormalEgo 1)
				(HandsOff)
				(ego
					posn: (ego x?) (+ (ego y?) 16)
					setPri: 10
					setMotion: MoveTo 85 153 self
				)
			)
			(23 (= cycles 22))
			(24
				(aBooth setMotion: MoveTo 111 211 self)
				(= cycles 33)
			)
			(25
				(Bset 21)
				(ego setMotion: MoveTo 107 153 self)
				(Format @plotString 220 13)
				(= seconds (localproc_000c))
			)
			(26 (= cycles 33))
			(27 (aBooth stopUpd:))
			(28
				(NormalEgo 0)
				(PutInRoom 3 210)
				(PutInRoom 1 216)
				(= gCurRoomNum 0)
				(gTheMusic number: 299 loop: global72 play:)
			)
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
		(if (Said 'look>')
			(if (Said '/building,office,club') (Print 220 0))
			(if (Said '[/area]') (Print 220 1))
		)
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if
				(and
					(> (event x?) 238)
					(< (event x?) 305)
					(> (event y?) 80)
					(< (event y?) 107)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 321 73)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 284)
					(< (event x?) 319)
					(> (event y?) 114)
					(< (event y?) 144)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 321 137)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 6)
					(< (event x?) 311)
					(> (event y?) 21)
					(< (event y?) 181)
				)
				(event claimed: 1)
				(switch theCursor
					(998 (Print 220 1))
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 0)
					(< (event x?) 19)
					(> (event y?) 137)
					(< (event y?) 155)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -3 145)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 0)
					(< (event x?) 18)
					(> (event y?) 164)
					(< (event y?) 184)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo -3 175)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 302)
					(< (event x?) 319)
					(> (event y?) 173)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 322 183)
					)
					(else  (event claimed: 0))
				)
			)
		)
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 240
		view 220
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
		view 220
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
				(aCredit2 loop: 2 setCycle: End)
				(= cycles 22)
			)
			(5
				(Bset 28)
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

(instance aBooth of Act
	(properties
		y 211
		x 111
		view 221
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setLoop: 0 setPri: 11)
	)
)

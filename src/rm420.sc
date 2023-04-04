;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use DCIcon)
(use Follow)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm420 0
)
(synonyms
	(man guard man man man bouncer)
	(babe babe dale cheri)
)

(local
	[msgBuf 166]
	[titleBuf 22]
	passPage
	inputPassNum
	[correctPassNum 24] = [0 0 0 741 0 -9725 30004 0 0 18608 25695 -32695 993 0 0 9170 0 0 -16422 -31742 0 0 -10054 -3033]
	local214
)
(procedure (localproc_0032 param1 param2 param3)
	(Print
		@msgBuf
		#at
		-1
		10
		#title
		{the Maitre d' says}
		#mode
		1
		#icon
		param1
		param2
		param3
	)
	(= msgBuf 0)
)

(instance rm420 of Rm
	(properties
		picture 420
		east 415
	)
	
	(method (init)
		(Load rsSOUND 11)
		(if (ego has: 7) (Load rsVIEW 7))
		(super init:)
		(User canInput: 0 mapKeyToDir: 0)
		(addToPics
			add: atpPoster1
			add: atpPoster2
			add: atpPhone
			add: atpPodium
			doit:
		)
		(aRope init:)
		(aDoor init:)
		(NormalEgo)
		(ego observeControl: 16384 8192 init:)
		(self setScript: RoomScript)
		(if
			(or
				(== prevRoomNum 430)
				(== prevRoomNum 431)
				(== gameMinutes 0)
			)
			(aMaitreD view: 422 setPri: 5 setLoop: 2 init:)
		)
		(if (< gameMinutes 3)
			(aManager init:)
			(= roomSeconds aDoor)
		)
		(if
			(or
				(== gameMinutes 1)
				(== gameMinutes 2)
				(== gameMinutes 3)
			)
			(aCherri init:)
			(= roomSeconds aDoor)
		)
		(cond 
			((== prevRoomNum 440)
				(if (== gCurRoomNum 18) (= gCurRoomNum 0))
				(TheMenuBar draw:)
				(SL enable:)
				(ego loop: 0 posn: 44 142)
			)
			((== prevRoomNum 435)
				(= gCurRoomNum 0)
				(ego
					loop: egoName
					posn: (if currentEgoView else 99) (if gameSeconds else 124)
				)
				(aCherri init:)
			)
			((== prevRoomNum 430) (RoomScript changeState: 1))
			((== prevRoomNum 431) (self style: 7) (RoomScript changeState: 1))
			((> (ego y?) 130) (ego posn: 317 188 loop: 1))
			(else (ego loop: 1 posn: 309 163))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== roomSeconds aDoor)
			(= roomSeconds 0)
			(DisposeScript 421)
			(DisposeScript 422)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(TheMenuBar draw:)
				(SL enable:)
				(aRope setCel: 255 stopUpd:)
				(aMaitreD view: 422 posn: 125 109 setPri: 5 init:)
				(NormalEgo 2)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					posn: 150 94
					setMotion: MoveTo 150 114 self
				)
			)
			(2
				(ego stopUpd:)
				(aRope setCycle: Beg)
				(aMaitreD
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 172 105 self
				)
			)
			(3
				(aMaitreD setLoop: 2 setCycle: Fwd)
				(= seconds 3)
			)
			(4
				(aMaitreD setCel: 0 stopUpd:)
				(aRope stopUpd:)
				(NormalEgo)
				(ego observeControl: 16384 8192)
				(if (== prevRoomNum 430)
					(Print 420 20)
				else
					(Print 420 21)
					(= seconds 2)
				)
			)
			(5
				(Print 420 22)
				(Print 420 23 #at -1 144)
			)
			(6
				(Ok)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 44 140 self
				)
			)
			(7
				(aDoor setCycle: End self)
				(ego setLoop: 1)
			)
			(8
				(ego setMotion: MoveTo -3 140 self)
			)
			(9 (aDoor setCycle: Beg self))
			(10
				(orchidSeconds number: 11 loop: 1 play:)
				(= cycles 12)
			)
			(11 (curRoom newRoom: 440))
			(12
				(HandsOff)
				(aCherri setScript: 0)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 50 143 self
				)
			)
			(13
				(ego loop: 1)
				(= cycles 11)
			)
			(14
				(if (== gameMinutes 4)
					(Print 420 24)
					(= seconds 3)
				else
					(= cycles (= seconds 0))
					((aManager script?) cue:)
					(self dispose:)
				)
			)
			(15
				(Print 420 25)
				(Print 420 26)
				(Print 420 27)
				(= seconds 3)
			)
			(16
				(Print 420 28)
				(Print 420 29)
				(= seconds 3)
			)
			(17
				(Print 420 30)
				(if (ego has: 7) (= state 19))
				(= seconds 3)
			)
			(18
				(Print 420 31)
				(= seconds 3)
			)
			(19
				(Print 420 32)
				(Print 420 33)
				(NormalEgo)
				(ego observeControl: 16384 8192)
			)
			(20
				(Print 420 34 #icon 7 0 0)
				(PutInRoom 7)
				(theGame changeScore: 25)
				(= seconds 3)
			)
			(21
				(Print 420 35 #at 10 5 #width 290)
				(= seconds 3)
			)
			(22 (aDoor setCycle: End self))
			(23
				(aDoor stopUpd:)
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(24 (aDoor setCycle: Beg self))
			(25
				(orchidSeconds number: 11 loop: 1 play:)
				(cls)
				(Print 420 36)
				(curRoom newRoom: 440)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(cond 
			((Said 'look/art,art') (Print 420 0))
			(
			(or (Said 'make/call') (Said 'call') (Said 'use/call'))
				(if (== gameMinutes 2)
					(Print 420 1)
				else
					(Print 420 2)
				)
			)
			((Said '/alter') (Print 420 3))
			((Said 'unbolt/door') (Print 420 4))
			((Said 'open/door')
				(cond 
					((not (& (ego onControl:) $0008)) (NotClose))
					((== gCurRoomNum 11) (self changeState: 6))
					(else (Print 420 5))
				)
			)
			(
			(and (== gCurRoomNum 11) (Said '/cloth,cloth')) (Print 420 6))
			((Said 'knock')
				(cond 
					((== gCurRoomNum 11) (Print 420 7))
					((or (Btst 35) (>= gameMinutes 6)) (Print 420 8))
					((not (& (ego onControl:) $0008)) (NotClose))
					((== gameMinutes 1) (Print 420 9))
					(else (self changeState: 12))
				)
			)
			((Said 'look<in/bolt,(hole<key),door')
				(if (not (& (ego onControl:) $0008))
					(NotClose)
				else
					(Print 420 10)
				)
			)
			((Said 'look,look/awning,door')
				(if (not (& (ego onControl:) $0008))
					(NotClose)
				else
					(Print 420 11)
				)
			)
			((Said 'board/backstage') (Print 420 12))
			((Said '/hemp') (Print 420 13))
			((Said 'look>')
				(cond 
					((Said '/lectern')
						(if (== gameMinutes 2)
							(Print 420 14)
						else
							(Print 420 15)
						)
					)
					((Said '/backstage') (Print 420 16))
					((Said '/wall') (Print 420 0) (Print 420 17))
					((Said '/call')
						(if (== gameMinutes 2)
							(Print 420 18)
						else
							(Print 420 17)
						)
					)
					((Said '[/area]')
						(Print
							(Format
								@msgBuf
								420
								19
								(cond 
									((== gameMinutes 2) {a gorgeous woman in a dressing gown})
									((cast contains: aMaitreD) {a man standing behind a podium})
									(else {you})
								)
							)
						)
					)
				)
			)
		)
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
			)
			(if
				(and
					(> (event x?) 292)
					(< (event x?) 319)
					(> (event y?) 133)
					(< (event y?) 189)
				)
				(event claimed: 1)
				(switch theCursor
					(999
						(ego setMotion: MoveTo 324 187 self)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(proc0_26 aMaitreD (event x?) (event y?))
					(cast contains: aMaitreD)
				)
				(switch theCursor
					(996
						(event claimed: 1)
						(cond 
							((!= gCurRoomNum 0) (GoodIdea))
							((not (& (ego onControl:) $0010)) (Print 420 50))
							((>= gameMinutes 5) (Print 420 44))
							((>= gameMinutes 1) (Print 420 45))
							(local214
								(= temp0
									(Print
										{What do you want to ask about?}
										#button
										{Coupon}
										1
										#button
										{Bar}
										2
										#button
										{Casino}
										3
										#button
										{Show}
										4
									)
								)
								(switch temp0
									(1
										(cond 
											((!= gCurRoomNum 0) (GoodIdea))
											((not (& (ego onControl:) $0010)) (Print 420 47))
											((>= gameMinutes 5) (Print 420 44))
											((>= gameMinutes 1) (Print 420 45))
											(else (MaitreDScript changeState: 3))
										)
									)
									(2
										(if (not musicLoop)
											(if
												(cond 
													((!= gCurRoomNum 0) (GoodIdea))
													((not (& (ego onControl:) $0010)) (Print 420 37))
													(else
														(Print 420 40)
														(Format @msgBuf 420 41)
														(MaitreDScript changeState: 4)
													)
												)
												1
											)
										)
									)
									(3
										(cond 
											((!= gCurRoomNum 0) (GoodIdea))
											((not (& (ego onControl:) $0010)) (Print 420 37))
											(else
												(Print 420 42)
												(Format @msgBuf 420 43)
												(MaitreDScript changeState: 4)
											)
										)
									)
									(4
										(cond 
											((!= gCurRoomNum 0) (GoodIdea))
											((not (& (ego onControl:) $0010)) (Print 420 37))
											((>= gameMinutes 5) (Print 420 44))
											((>= gameMinutes 1) (Print 420 45))
											(else (Format @msgBuf 420 46) (MaitreDScript changeState: 4))
										)
									)
								)
							)
							(else
								(= local214 1)
								(Printf 420 51 global171)
								(Print 420 52)
								(Format @msgBuf 420 46)
								(MaitreDScript changeState: 4)
							)
						)
					)
					(998
						(event claimed: 1)
						(Print 420 53)
						(Print 420 54 #at -1 144)
					)
					(6
						(event claimed: 1)
						(cond 
							((!= gCurRoomNum 0) (GoodIdea))
							((not (& (ego onControl:) $0010)) (Print 420 47))
							((>= gameMinutes 5) (Print 420 44))
							((>= gameMinutes 1) (Print 420 45))
							(else
								(= gTheCursor 900)
								(theGame setCursor: 998 (HaveMouse))
								(MaitreDScript changeState: 3)
							)
						)
						(cond 
							((!= gCurRoomNum 0) (GoodIdea))
							((not (ego has: 6)) (Print 420 48))
							((not (& (ego onControl:) $0010)) (Print 420 47))
							((>= gameMinutes 5) (Print 420 49))
							(else (MaitreDScript changeState: 6))
						)
					)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 19)
					(< (event x?) 42)
					(> (event y?) 85)
					(< (event y?) 131)
				)
				(event claimed: 1)
				(switch theCursor
					(7
						(cond 
							((== gCurRoomNum 11) (Print 420 7))
							((or (Btst 35) (>= gameMinutes 6)) (Print 420 8))
							((not (& (ego onControl:) $0008)) (NotClose))
							((== gameMinutes 1) (Print 420 9))
							(else
								(= gTheCursor 900)
								(theGame setCursor: 998 (HaveMouse))
								(self changeState: 12)
							)
						)
					)
					(995
						(cond 
							((== gCurRoomNum 11) (Print 420 7) (self changeState: 6))
							((or (Btst 35) (>= gameMinutes 6)) (Print 420 8))
							((not (& (ego onControl:) $0008)) (NotClose))
							((== gameMinutes 1) (Print 420 9))
							(else
								(= gTheCursor 900)
								(theGame setCursor: 998 (HaveMouse))
								(self changeState: 12)
							)
						)
					)
					(994 (Print 420 4))
					(else  (event claimed: 0))
				)
			)
			(if (proc0_26 atpPoster1 (event x?) (event y?))
				(event claimed: 1)
				(switch theCursor
					(995)
					(998 (Print 420 0))
					(994)
					(else  (event claimed: 0))
				)
			)
			(if (proc0_26 atpPoster2 (event x?) (event y?))
				(event claimed: 1)
				(switch theCursor
					(995)
					(998 (Print 420 0))
					(994)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 297)
					(< (event x?) 300)
					(> (event y?) 149)
					(< (event y?) 188)
				)
				(event claimed: 1)
				(switch theCursor
					(998
						(Print
							(Format
								@msgBuf
								420
								19
								(cond 
									((== gameMinutes 2) {a gorgeous woman in a dressing gown})
									((cast contains: aMaitreD) {a man standing behind a podium})
									(else {you})
								)
							)
						)
					)
					(994)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(> (event x?) 64)
					(< (event x?) 73)
					(> (event y?) 73)
					(< (event y?) 94)
				)
				(event claimed: 1)
				(switch theCursor
					(995 (Print 420 3))
					(998
						(if (== gameMinutes 2)
							(Print 420 1)
						else
							(Print 420 2)
						)
					)
					(994)
					(996)
					(else  (event claimed: 0))
				)
			)
			(if
				(and
					(proc0_26 aCherri (event x?) (event y?))
					(cast contains: aCherri)
				)
				(event claimed: 1)
				(switch theCursor
					(995)
					(998
						(cond 
							((!= gameMinutes 2) (Print 422 1))
							((!= gCurRoomNum 0) (GoodIdea))
							((!= (aCherri xLast?) (aCherri x?)) (Print 422 2))
							((not (& (ego onControl:) $0020)) (Print 422 3))
							(else
								(CherriScript2 changeState: 1)
								(CherriScript2 changeState: 2)
							)
						)
					)
					(994)
					(996)
					(2 (Print 420 58))
					(4 (Print 420 59))
					(3 (Print 420 60))
					(11 (Print 420 61))
					(1 (Print 420 62))
					(else  (event claimed: 0))
				)
			)
		)
	)
)

(instance CherriScript2 of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 10) (> (ego x?) 280))
			(CherriScript2 cue:)
		)
		(if
			(and
				(== gameMinutes 2)
				(== (aCherri loop?) 4)
				(== (aCherri x?) 82)
				(== (aCherri y?) 124)
			)
			(switch (Random 0 6)
				(0 (aCherri setCel: 0))
				(1 (aCherri setCycle: Fwd))
			)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 3 2)
		(switch (= state newState)
			(0
				(if (== gameMinutes 3) (CherriScript2 changeState: 3))
				(if (== gameMinutes 1)
					(CherriScript2 changeState: 10)
					(aCherri posn: -20 143 stopUpd:)
				)
			)
			(1
				(Ok)
				(HandsOff)
				(= gCurRoomNum 14)
				(Printf 422 5 global171)
				(aCherri setStep: 0 0 setMotion: Follow ego 222)
				(= seconds 3)
			)
			(2
				(if (not (Btst 48))
					(Bset 48)
					(theGame changeScore: 5)
				)
				(Print 422 6)
				(= currentEgoView (ego x?))
				(= gameSeconds (ego y?))
				(= egoName (ego loop?))
				(curRoom newRoom: 435)
			)
			(3 (HandsOff) (= seconds 3))
			(4 (Print 422 7) (= seconds 3))
			(5
				(Print 422 8)
				(aCherri
					illegalBits: 0
					ignoreActors: 0
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 45 140 self
				)
				(if
					(and
						(> (ego y?) (aCherri y?))
						(< (ego x?) (+ (aCherri x?) 15))
					)
					(ego setCycle: Walk setMotion: MoveTo 97 (ego y?))
				)
			)
			(6
				(roomSeconds setCycle: End self)
			)
			(7
				(Print 422 9)
				(roomSeconds stopUpd:)
				(aCherri setMotion: MoveTo -20 140 self)
			)
			(8
				(roomSeconds setCycle: Beg self)
			)
			(9
				(orchidSeconds number: 11 loop: 1 play:)
				(= gameMinutes 4)
				(roomSeconds stopUpd:)
				(NormalEgo)
				(ego observeControl: 16384 8192)
				(aCherri dispose:)
				(self dispose:)
			)
			(10 (= seconds 15))
			(11
				(if (< (ego x?) 160)
					(-- state)
					(= cycles 2)
				else
					(HandsOff)
					(roomSeconds setCycle: End self)
					(= seconds 0)
				)
			)
			(12
				(roomSeconds stopUpd:)
				(aCherri
					posn: 13 140
					loop: 0
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 45 140 self
				)
			)
			(13
				(roomSeconds setCycle: Beg self)
			)
			(14
				(orchidSeconds number: 11 loop: 1 play:)
				(roomSeconds stopUpd:)
				(aCherri setMotion: MoveTo 82 124 self)
			)
			(15
				(aCherri loop: 4)
				(= gameMinutes 2)
				(HandsOn)
				(ego observeControl: 16384 8192)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(!= (event type?) evSAID)
				(!= gameMinutes 2)
				(event claimed?)
			)
			(return)
		)
		(cond 
			((or (Said 'give/babe') (Said 'give/*/babe')) (Print 422 0))
			((Said 'look/babe')
				(cond 
					((!= gameMinutes 2) (Print 422 1))
					((!= gCurRoomNum 0) (GoodIdea))
					((!= (aCherri xLast?) (aCherri x?)) (Print 422 2))
					((not (& (ego onControl:) $0020)) (Print 422 3))
					(else (CherriScript2 changeState: 1))
				)
			)
			((and (== gameMinutes 2) (Said '/babe')) (Print 422 4))
		)
	)
)

(instance aMaitreD of Act
	(properties
		y 105
		x 172
		view 422
		loop 2
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: MaitreDScript ignoreActors: stopUpd:)
	)
)

(instance MaitreDScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 5])
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0)
			(1
				(Ok)
				(HandsOff)
				(Print 420 64)
				(aMaitreD setCycle: Fwd)
				(= seconds 3)
			)
			(2
				(while (== 0 [correctPassNum passPage])
					(= passPage (Random 1 24))
				)
				(Format @msgBuf 420 65 passPage)
				(localproc_0032 422 3 0)
				(= temp0 0)
				(GetInput @temp0 7 {My pass number is:})
				(if
					(!=
						(= inputPassNum (ReadNumber @temp0))
						[correctPassNum passPage]
					)
					(= state 12)
				)
				(if global64
					(Printf
						420
						66
						inputPassNum
						[correctPassNum passPage]
						passPage
					)
				)
				(Format @msgBuf 420 67)
				(localproc_0032 422 3 0)
				(= seconds 3)
			)
			(3
				(aMaitreD setCel: 0 stopUpd:)
				(Bset 46)
				(if (not (Btst 47))
					(Format @msgBuf 420 68)
					(handIcon view: 422 loop: 4)
					(Print
						@msgBuf
						#at
						-1
						10
						#title
						{the Maitre d' says}
						#mode
						1
						#icon
						handIcon
					)
					(= msgBuf 0)
					(HandsOn)
				else
					(Format @msgBuf 420 69)
					(localproc_0032 422 3 6)
					(self changeState: 8)
				)
			)
			(4
				(HandsOff)
				(aMaitreD setLoop: 2 setCycle: Fwd)
				(= seconds 3)
			)
			(5
				(aMaitreD setCel: 0 stopUpd:)
				(if (not msgBuf) (Format @msgBuf 420 70))
				(localproc_0032 422 3 0)
				(HandsOn)
			)
			(6
				(HandsOff)
				(aMaitreD setLoop: 2 setCycle: Fwd)
				(= cycles 0)
				(= seconds 3)
			)
			(7
				(aMaitreD setCel: 0 stopUpd:)
				(cond 
					((== ((Inv at: 6) view?) 24) (Print 420 71) (localproc_0032 422 3 0))
					((not (Btst 46))
						(Format @msgBuf 420 72)
						(localproc_0032 422 3 1)
						(PutInRoom 6)
						(theGame changeScore: 50)
						(Bset 47)
						(HandsOn)
					)
					(else
						(HandsOff)
						(Format @msgBuf 420 72)
						(localproc_0032 422 3 1)
						(PutInRoom 6)
						(Bset 47)
						(theGame changeScore: 50)
						(= seconds 2)
					)
				)
			)
			(8
				(aMaitreD
					illegalBits: 0
					ignoreActors: 1
					setLoop: 1
					setCycle: Fwd
					setMotion: MoveTo 162 105 self
				)
			)
			(9
				(aMaitreD setMotion: MoveTo 125 109 self)
				(aRope cycleSpeed: 1 setCycle: End)
			)
			(10
				(aRope stopUpd:)
				(aMaitreD setLoop: 2 setCel: 0 stopUpd:)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 149 114 self
				)
			)
			(11
				(ego setMotion: MoveTo 149 94 self)
			)
			(12 (curRoom newRoom: 430))
			(13
				(aMaitreD setCel: 0 stopUpd:)
				(Format @msgBuf 420 74)
				(localproc_0032 422 3 0)
				(= seconds 2)
			)
			(14
				(Format @msgBuf 420 75)
				(localproc_0032 422 3 0)
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 56
					register: (Format @msgBuf 420 76)
					next: (Format @titleBuf 420 77)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((or (Said 'ask/pass') (Said 'ask/about/pass'))
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0010)) (Print 420 37))
					(else
						(Print 420 38)
						(Format @msgBuf 420 39)
						(self changeState: 4)
					)
				)
			)
			((and (not musicLoop) (Said '/entertainer'))
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0010)) (Print 420 37))
					(else
						(Print 420 40)
						(Format @msgBuf 420 41)
						(self changeState: 4)
					)
				)
			)
			(
				(or
					(Said '/casino,gambling')
					(Said 'gamble')
					(Said '//casino,gambling')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0010)) (Print 420 37))
					(else
						(Print 420 42)
						(Format @msgBuf 420 43)
						(self changeState: 4)
					)
				)
			)
			(
				(or
					(Said 'board/backstage')
					(Said 'ask/show')
					(Said 'ask//show')
					(Said 'look/show')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0010)) (Print 420 37))
					((>= gameMinutes 5) (Print 420 44))
					((>= gameMinutes 1) (Print 420 45))
					(else (Format @msgBuf 420 46) (self changeState: 4))
				)
			)
			((Said 'bracelet,use,give,show/pass,book')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0010)) (Print 420 47))
					((>= gameMinutes 5) (Print 420 44))
					((>= gameMinutes 1) (Print 420 45))
					(else (self changeState: 1))
				)
			)
			(
				(or
					(Said 'bracelet,give,show/buck,bill')
					(Said 'buy,tip/man')
					(Said 'bracelet,give,show/man/buck,bill')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (ego has: 6)) (Print 420 48))
					((not (& (ego onControl:) $0010)) (Print 420 47))
					((>= gameMinutes 5) (Print 420 49))
					(else (self changeState: 6))
				)
			)
			((Said 'address/man')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0010)) (Print 420 50))
					((>= gameMinutes 5) (Print 420 44))
					((>= gameMinutes 1) (Print 420 45))
					(else
						(Printf 420 51 global171)
						(Print 420 52)
						(Format @msgBuf 420 46)
						(self changeState: 4)
					)
				)
			)
			((Said 'look/man') (Print 420 53) (Print 420 54 #at -1 144))
			((Said 'give>')
				(= inventorySaidMe (inventory saidMe:))
				(event claimed: 0)
				(cond 
					((not (& (ego onControl:) $0010)) (Print 420 55))
					((Said '[/!*]') (Print 420 56))
					((not inventorySaidMe) (Print 420 57))
					((not (inventorySaidMe ownedBy: ego)) (DontHave))
					((== inventorySaidMe (inventory at: 2)) (Print 420 58))
					((== inventorySaidMe (inventory at: 4)) (Print 420 59))
					((== inventorySaidMe (inventory at: 3)) (Print 420 60))
					((== inventorySaidMe (inventory at: 11)) (Print 420 61))
					((== inventorySaidMe (inventory at: 1)) (Print 420 62))
					(else (Print 420 63))
				)
				(event claimed: 1)
			)
		)
	)
)

(instance atpPhone of PV
	(properties
		y 96
		x 70
		view 420
		loop 1
		cel 2
		priority 7
	)
)

(instance atpPoster1 of PV
	(properties
		y 87
		x 224
		view 420
		loop 1
		priority 7
	)
)

(instance atpPoster2 of PV
	(properties
		y 93
		x 279
		view 420
		loop 1
		cel 1
		priority 7
	)
)

(instance atpPodium of PV
	(properties
		y 109
		x 166
		view 420
		loop 2
		priority 6
		signal $4000
	)
)

(instance aRope of Prop
	(properties
		y 91
		x 106
		view 420
		loop 3
		signal $4000
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 6 stopUpd:)
	)
)

(instance aManager of Prop
	(properties
		y 1000
		x 1000
		view 421
	)
	
	(method (init)
		(super init:)
		(self setScript: (ScriptID 421) stopUpd:)
	)
)

(instance aDoor of Prop
	(properties
		y 132
		x 42
		view 420
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance aCherri of Act
	(properties
		y 124
		x 82
		view 436
		loop 1
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: (ScriptID 422))
	)
)

(instance handIcon of DCIcon
	(properties)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
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
	rm330 0
)
(synonyms
	(man man dale)
)

(local
	local0
	local1
	local2
	theDrinker
	local4
	local5
	local6
)
(instance rm330 of Rm
	(properties
		picture 330
		east 230
	)
	
	(method (init)
		(Bset 36)
		(= gCurRoomNum 17)
		(Load rsVIEW 330)
		(Load rsVIEW 333)
		(Load rsVIEW 332)
		(Load rsVIEW 337)
		(Load rsVIEW 336)
		(Load rsVIEW 14)
		(Load rsSOUND 330)
		(Load rsSOUND 331)
		(Load rsSOUND 332)
		(Load rsSOUND 10)
		(Load rsSCRIPT 991)
		(Load rsSCRIPT 969)
		(super init:)
		(addToPics add: atpPchair add: atpDchair doit:)
		(aDale init:)
		(aClothes init:)
		(aDrinker1 init:)
		(aDrinker2 init:)
		(aDrinker3 init:)
		(aDrinker4 init:)
		(aCurtain init:)
		(aPanties init:)
		(self setScript: RoomScript)
		(User canInput: 0 canControl: 1 mapKeyToDir: 0)
		(if (== prevRoomNum 335)
			(HandsOff)
			(ego
				view: 332
				setLoop: 4
				setCel: 255
				posn: 281 140
				illegalBits: 0
				init:
			)
			(RoomScript changeState: 3)
			(aDale
				view: 337
				setLoop: 4
				setCel: 255
				posn: 283 121
				cycleSpeed: 1
			)
			(DaleScript changeState: 24)
		else
			(NormalEgo)
			(ego
				view: 332
				loop: 1
				posn: 317 129
				baseSetter: squareBase
				init:
			)
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 281 140 self
				)
			)
			(2
				(ego setLoop: 4 setPri: 3 setCel: 0 setCycle: End self)
			)
			(3
				(User canInput: 1)
				(= gCurRoomNum 1004)
				(if local1
					(= cycles 0)
					(= seconds 3)
				else
					(= local1 1)
					(DaleScript changeState: 1)
				)
			)
			(4
				(if (== local2 2)
					(ego loop: 5 setCycle: Fwd)
					(= cycles (Random 11 33))
				else
					(self changeState: 6)
				)
			)
			(5
				(ego setLoop: 4 setCel: 255)
				(= cycles (Random 11 33))
			)
			(6
				(ego setLoop: 6 cel: 0 setCycle: End self)
			)
			(7 (= cycles (Random 33 66)))
			(8
				(ego setCycle: Beg self)
				(= state 3)
			)
			(9
				(HandsOff)
				(= cycles (= seconds 0))
				(ego view: 332 setLoop: 4 setCel: 255 setCycle: Beg self)
			)
			(10
				(ego
					setCycle: Walk
					setLoop: 1
					setMotion: MoveTo 282 132 self
				)
			)
			(11
				(= gCurRoomNum 1004)
				(NormalEgo 1 332)
				(ego baseSetter: squareBase)
			)
			(12
				(Ok)
				(Print 330 38)
				(= cycles (= seconds 0))
				(HandsOff)
				(ego
					view: 336
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(13
				(ego setLoop: 1 cel: 0 setCycle: Fwd)
				(= cycles 15)
			)
			(14
				(ego cycleSpeed: 0)
				(= cycles 15)
			)
			(15 (= cycles 10))
			(16
				(Print 330 39 #icon 14 0 0 #at -1 10)
				(ego cycleSpeed: 1 setLoop: 2 cel: 0 setCycle: End)
				(PutInRoom 14)
				(aPanties
					posn: (ego x?) (ego y?)
					setMotion: JumpTo 42 96 self
				)
			)
			(17
				(Print 330 40)
				(= cycles (= seconds 0))
				(aPanties stopUpd:)
				(theGame changeScore: 100)
				(User canInput: 1)
				(ego view: 332)
				(if (== gCurRoomNum 1004)
					(self changeState: 2)
				else
					(NormalEgo 1 332)
					(ego baseSetter: squareBase)
				)
			)
			(18
				(Ok)
				(HandsOff)
				(= gCurRoomNum 1004)
				(if (not (Btst 56))
					(Print 330 41)
					(= seconds 3)
				else
					(self cue:)
				)
			)
			(19
				(if (not (Btst 56))
					(Bset 56)
					(theGame changeScore: 1)
					(Print 330 42)
				)
				(curRoom newRoom: 335)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if local2
			(cond 
				((Said 'caress,get,grab,caress/man') (Print 330 0))
				((Said 'applaud')
					(Print 330 1)
					(if (== gCurRoomNum 1004) (self changeState: 4))
				)
				((Said '(get<off),drain/panties') (Print 330 2))
				((Said 'give,throw/panties>')
					(cond 
						((not (ego has: 14)) (DontHave))
						((Said '/[/!*]') (Print 330 3))
						((not (Said '//backstage,man')) (Print 330 4))
						((!= local2 2) (Print 330 5))
						(else (self changeState: 12))
					)
					(event claimed: 1)
				)
				((Said 'look/man,eye')
					(cond 
						((== local2 0) (Print 330 6))
						((< local2 6) (Print 330 7) (Print 330 8 #at -1 144))
						((> local2 6) (Print 330 9))
						((!= gCurRoomNum 1004) (Print 330 10))
						(else (self changeState: 18))
					)
				)
				((Said 'address/man')
					(switch local2
						(2 (Print 330 11))
						(3 (Print 330 12))
						(4 (Print 330 13))
						(5 (Print 330 14) (= local5 1))
						(1 (Print 330 12))
						(6 (Print 330 15))
						(else  (Print 330 16))
					)
				)
				((Said 'address') (Print 330 17))
				(
					(and
						(== local2 6)
						(or
							(Said 'eat,eat,eat/man')
							(Said 'caress,caress,caress/man')
						)
					)
					(Print 330 18)
				)
			)
		)
		(cond 
			((and local0 (Said 'get,(up<pick)/cloth')) (Print 330 19))
			((Said 'get,buy/drink,cup') (Print 330 20))
			(
				(or
					(Said 'go,climb,(get<on)/backstage,buffet,barstool')
					(Said 'whistle,holler')
				)
				(Print 330 21)
			)
			((Said 'lie')
				(cond 
					((& (ego onControl:) $0004) (Print 330 22))
					((not (& (ego onControl:) $0002)) (NotClose))
					((== gCurRoomNum 1004) (YouAre))
					((!= gCurRoomNum 17) (GoodIdea))
					(else (Ok) (self changeState: 1))
				)
			)
			(
				(or
					(Said 'nightstand,(get<off),(get<up),(nightstand<up)')
					(Said 'exit/barstool')
				)
				(cond 
					((== gCurRoomNum 17) (YouAre))
					((!= gCurRoomNum 1004) (GoodIdea))
					((== local2 6) (Print 330 23))
					(else (self changeState: 9))
				)
			)
			((Said 'throw/buck,tip,buck') (Print 330 24))
			((Said 'look,caress<below/buffet,barstool') (Print 330 25))
			((Said 'look>')
				(cond 
					((and local0 (Said '/cloth,buffet')) (Print 330 26))
					((Said '/backstage') (Print 330 27))
					((Said '/barstool')
						(Print 330 28)
						(if (== gCurRoomNum 1004) (Print 330 29))
						(if (== local2 6)
							(Print 330 30)
							(Print 330 31 #at -1 144)
						)
					)
					((Said '/burn') (Print 330 32))
					((Said '/curtain') (if (aCurtain cel?) (Print 330 33) else (Print 330 34)))
					((Said '/buffet,babe,man,cup,drink,couple') (Print 330 35))
					((Said '[/area]') (Print 330 36) (Print 330 37 #at -1 144))
				)
			)
			((== (event type?) evMOUSEBUTTON)
				(if (& (event modifiers?) emSHIFT)
					(switch theCursor
						(gTheCursor
							(theGame setCursor: 999 (HaveMouse))
							(event claimed: 1)
						)
						(991
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(999
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(993
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(996
							(theGame setCursor: 994 (HaveMouse))
							(event claimed: 1)
						)
						(998
							(theGame setCursor: 995 (HaveMouse))
							(event claimed: 1)
						)
						(995
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(994
							(if
								(or
									(== gTheCursor 900)
									(== gTheCursor 994)
									(== gTheCursor 666)
									(== gTheCursor 992)
									(== gTheCursor 993)
								)
								(theGame setCursor: 999 (HaveMouse))
							else
								(theGame setCursor: gTheCursor (HaveMouse))
								(= theCursor gTheCursor)
							)
							(event claimed: 1)
						)
					)
				else
					(if
						(and
							(> (event x?) 119)
							(< (event x?) 270)
							(> (event y?) 21)
							(< (event y?) 170)
						)
						(event claimed: 1)
						(switch theCursor
							(998
								(Print 330 36)
								(Print 330 37 #at -1 144)
							)
							(else  (event claimed: 0))
						)
					)
					(if
						(and
							(> (event x?) 311)
							(< (event x?) 319)
							(> (event y?) 105)
							(< (event y?) 177)
						)
						(event claimed: 1)
						(switch theCursor
							(999
								(ego setMotion: MoveTo 321 145)
							)
							(else  (event claimed: 0))
						)
					)
					(if
						(or
							(proc0_26 aDale (event x?) (event y?))
							(and
								(> (event x?) 37)
								(< (event x?) 106)
								(> (event y?) 50)
								(< (event y?) 121)
							)
						)
						(event claimed: 1)
						(switch theCursor
							(14
								(cond 
									((not (ego has: 14)) (DontHave))
									((!= local2 2) (Print 330 5))
									(else
										(= gTheCursor 900)
										(theGame setCursor: 998 (HaveMouse))
										(self changeState: 12)
									)
								)
								(event claimed: 1)
							)
							(998
								(cond 
									((== local2 0) (Print 330 6))
									((< local2 6) (Print 330 7) (Print 330 8 #at -1 144))
									((> local2 6) (Print 330 9))
									((!= gCurRoomNum 1004) (Print 330 10))
									(else (RoomScript changeState: 18) (= gCurRoomNum 1004))
								)
							)
							(996
								(switch local2
									(2 (Print 330 11))
									(3 (Print 330 12))
									(4 (Print 330 13))
									(5 (Print 330 14) (= local5 1))
									(1 (Print 330 12))
									(6 (Print 330 15))
									(else  (Print 330 16))
								)
							)
							(994
								(cond 
									((not (ego has: 14)) (DontHave))
									((!= local2 2) (Print 330 5))
									(else
										(= gTheCursor 900)
										(theGame setCursor: 998 (HaveMouse))
										(self changeState: 12)
									)
								)
								(event claimed: 1)
							)
						)
					)
					(if
						(or
							(proc0_26 atpPchair (event x?) (event y?))
							(proc0_26 atpDchair (event x?) (event y?))
						)
						(InRoom 14)
						(event claimed: 1)
						(switch theCursor
							(998
								(cond 
									((== local2 0) (Print 330 6))
									((< local2 6) (Print 330 7) (Print 330 8 #at -1 144))
									((> local2 6) (Print 330 9))
									((!= gCurRoomNum 1004) (Print 330 10))
									(else (DaleScript changeState: 18))
								)
							)
							(else  (event claimed: 0))
						)
					)
					(if
						(and
							(> (event x?) 282)
							(< (event x?) 293)
							(> (event y?) 137)
							(< (event y?) 153)
						)
						(event claimed: 1)
						(switch theCursor
							(998
								(cond 
									((== local2 0) (Print 330 6))
									((< local2 6) (Print 330 7) (Print 330 8 #at -1 144))
									((> local2 6) (Print 330 9))
									((!= gCurRoomNum 1004) (Print 330 10))
									(else (DaleScript changeState: 18))
								)
							)
							(995
								(cond 
									((& (ego onControl:) $0004))
									((not (& (ego onControl:) $0002)) (NotClose))
									((== gCurRoomNum 1004) (RoomScript changeState: 9) (= gCurRoomNum 0))
									((!= gCurRoomNum 17) (GoodIdea))
									(else (Ok) (RoomScript changeState: 1) (= gCurRoomNum 1004))
								)
							)
							(else  (event claimed: 0))
						)
					)
					(if
						(or
							(proc0_26 aDrinker1 (event x?) (event y?))
							(proc0_26 aDrinker2 (event x?) (event y?))
							(proc0_26 aDrinker3 (event x?) (event y?))
							(proc0_26 aDrinker4 (event x?) (event y?))
						)
						(event claimed: 1)
						(switch theCursor
							(996 (Print 330 17))
							(else  (event claimed: 0))
						)
					)
					(if (proc0_26 aClothes (event x?) (event y?))
						(event claimed: 1)
						(switch theCursor
							(995 (Print 330 19))
							(else  (event claimed: 0))
						)
					)
					(if
						(and
							(== (event type?) evMOUSEBUTTON)
							(== gCurRoomNum 0)
						)
						(event claimed: 1)
						(cond 
							((== gCurRoomNum 17) (YouAre))
							((!= gCurRoomNum 1004) (RoomScript changeState: 1) (= gCurRoomNum 1004))
							((== local2 6)
								(Print 330 23)
								(RoomScript changeState: 9)
								(= gCurRoomNum 1004)
							)
							(else (RoomScript changeState: 9) (= gCurRoomNum 0))
						)
					)
					(if
						(and
							(== (event type?) evMOUSEBUTTON)
							(== gCurRoomNum 1004)
							(== theCursor 992)
						)
						(event claimed: 1)
						(cond 
							((== gCurRoomNum 17) (YouAre))
							((!= gCurRoomNum 1004)
								(GoodIdea)
								(RoomScript changeState: 9)
								(= gCurRoomNum 1004)
							)
							((== local2 6)
								(Print 330 23)
								(RoomScript changeState: 9)
								(= gCurRoomNum 1004)
							)
							(else (RoomScript changeState: 9) (= gCurRoomNum 0))
						)
					)
				)
			)
		)
	)
)

(instance aDale of Act
	(properties
		y 86
		x -86
		view 333
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd setScript: DaleScript)
	)
)

(instance DaleScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1 (= seconds (Random 2 5)))
			(2
				(gTheMusic number: 10 loop: -1 play:)
				(Print 330 43)
				(Print 330 44 #at -1 144)
				(aCurtain setCycle: End self)
			)
			(3
				(= currentStatus (theGame setSpeed: 6))
				(aCurtain stopUpd:)
				(gTheMusic number: 330 loop: -1 play:)
				(aDale setLoop: 0 setMotion: MoveTo 89 86 self)
				(= local2 1)
			)
			(4
				(aCurtain setCycle: Beg self)
				(aDale setLoop: 1 setCel: 0)
				(= local2 2)
			)
			(5
				(aCurtain stopUpd:)
				(aDale cycleSpeed: 1 setCycle: End self)
			)
			(6
				(aDale setLoop: 2 setCel: 0 setCycle: CT 6 1 self)
			)
			(7
				(aDale setCycle: End)
				(aClothes posn: 105 83 setMotion: JumpTo 248 133 self)
			)
			(8
				(aClothes stopUpd:)
				(= local0 1)
				(= cycles 20)
			)
			(9
				(aDale
					cycleSpeed: 1
					setLoop: 3
					setCel: 0
					setCycle: End self
				)
				(= local4 0)
			)
			(10
				(aDale setCycle: Fwd setLoop: 4)
				(= cycles 22)
			)
			(11
				(if (> 3 (++ local4)) (= state 9))
				(aDale
					setLoop: 5
					setMotion: MoveTo (Random 63 68) (Random 110 110)
				)
				(= cycles (Random 11 22))
			)
			(12
				(aDale setMotion: MoveTo 89 86 self)
			)
			(13
				(aDale setLoop: 6 cel: 0 setCycle: End)
				(gTheMusic number: 331 loop: 1 play:)
				(= cycles 33)
			)
			(14
				(Print 330 45 #at -1 10)
				(Print 330 46 #at -1 10)
				(= cycles (Random 11 44))
			)
			(15
				(aCurtain setCycle: End)
				(aDale setCycle: CT 8 -1)
				(gTheMusic number: 330 play:)
				(= cycles 22)
			)
			(16
				(aCurtain stopUpd:)
				(aDale
					cycleSpeed: 0
					setLoop: 5
					setCycle: Fwd
					setMotion: MoveTo 42 86 self
				)
				(= local2 3)
			)
			(17
				(if (InRoom 14 330)
					(aPanties hide:)
					((Inv at: 14) owner: 335)
				)
				(aDale setMotion: MoveTo -40 86 self)
			)
			(18
				(aDale stopUpd:)
				(aCurtain setCycle: Beg)
				(gTheMusic fade:)
				(= local2 4)
				(= seconds 5)
			)
			(19
				(theGame setSpeed: currentStatus)
				(aCurtain dispose:)
				(gTheMusic stop: number: 332 loop: global72 play:)
				(= seconds (Random 7 15))
			)
			(20
				(= local2 5)
				(aDale
					view: 337
					setLoop: -1
					setCycle: Walk
					ignoreActors: 0
					illegalBits: 0
					posn: 245 -10
					baseSetter: squareBase
					setMotion: MoveTo 316 129 self
				)
				(= cycles 20)
			)
			(21 (Print 330 47))
			(22
				(if (or local5 (InRoom 14 335))
					(= local2 6)
					(aDale
						ignoreActors:
						illegalBits: 0
						setMotion: MoveTo 283 121 self
					)
					(if (InRoom 14 335) (Print 330 48) else (Print 330 49))
				else
					(self changeState: 26)
				)
			)
			(23
				(aDale
					posn: 283 121
					cycleSpeed: 1
					setLoop: 4
					setCel: 0
					setCycle: End self
				)
			)
			(24 (= seconds 11))
			(25
				(if (!= gCurRoomNum 1004)
					(= seconds 15)
					(-- state)
				else
					(Print 330 50)
					(= local2 5)
					(aDale setCycle: Beg self)
				)
			)
			(26
				(aDale
					cycleSpeed: 0
					setStep: 3 2
					setCycle: Walk
					ignoreActors: 1
					illegalBits: 0
					setLoop: -1
					baseSetter: squareBase
					setMotion: MoveTo 333 143 self
				)
			)
			(27
				(= local2 7)
				(aDale dispose:)
			)
		)
	)
)

(instance aDrinker1 of Prop
	(properties
		y 8
		x 221
		view 330
		loop 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aDrinker2 of Prop
	(properties
		y 44
		x 239
		view 330
		loop 3
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aDrinker3 of Prop
	(properties
		y 77
		x 225
		view 330
		loop 4
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aDrinker4 of Prop
	(properties
		y 166
		x 264
		view 330
		loop 5
	)
	
	(method (init)
		(super init:)
		(self setScript: drinkerScript ignoreActors: stopUpd:)
	)
)

(instance drinkerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 5)))
			(1
				(switch (Random 1 4)
					(1 (= theDrinker aDrinker1))
					(2 (= theDrinker aDrinker2))
					(3 (= theDrinker aDrinker3))
					(4 (= theDrinker aDrinker4))
				)
				(theDrinker setCycle: End self)
			)
			(2 (= cycles (Random 15 29)))
			(3
				(theDrinker setCycle: Beg self)
			)
			(4 (self changeState: 0))
		)
	)
)

(instance atpPchair of PV
	(properties
		y 140
		x 281
		view 330
		loop 1
		priority 1
		signal $4000
	)
)

(instance atpDchair of PV
	(properties
		y 121
		x 281
		view 330
		loop 1
		cel 1
		priority 1
		signal $4000
	)
)

(instance aCurtain of Prop
	(properties
		y 88
		view 330
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 3 ignoreActors:)
	)
)

(instance aPanties of Act
	(properties
		y 1142
		x 283
		view 336
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 3
			setPri: 15
			setCycle: Fwd
			setStep: 10 6
			ignoreActors:
		)
	)
)

(instance aClothes of Act
	(properties
		y 1083
		x 105
		view 333
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 7
			setPri: 15
			setCycle: Fwd
			setStep: 5 5
			ignoreActors:
		)
	)
)

(instance squareBase of Code
	(properties)
	
	(method (doit param1)
		(param1 brBottom: (+ (param1 y?) 5))
		(param1 brTop: (- (param1 brBottom?) 9))
		(param1 brLeft: (- (param1 x?) 8))
		(param1 brRight: (+ (param1 x?) 8))
	)
)

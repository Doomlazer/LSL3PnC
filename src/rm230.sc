;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use DCIcon)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm230 0
)
(synonyms
	(man guard man man man bouncer)
)

(local
	local0
	confirmBJ
	[str 200]
)
(procedure (localproc_0026 param1 param2 param3)
	(Print
		@str
		#at
		-1
		10
		#title
		{the Doorman says:}
		#mode
		1
		#icon
		param1
		param2
		param3
	)
	(= str 0)
)

(instance rm230 of Rm
	(properties
		picture 230
		east 240
		south 220
	)
	
	(method (init)
		(if
			(and
				(InRoom 4)
				(ego has: 2)
				(== 21 ((Inv at: 2) view?))
			)
			(Load rsVIEW 231)
			(Load rsVIEW 21)
		)
		(super init:)
		(self setScript: RoomScript)
		(addToPics add: atpSign doit:)
		(aDoor init:)
		(if musicLoop
			(aSign init:)
			(aDoorman init:)
			(Load rsSOUND 11)
			(if (ego has: 6) (Load rsVIEW 25))
		)
		(NormalEgo)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
		(cond 
			((== prevRoomNum 220)
				(ego loop: 3)
				(switch (Random 1 3)
					(1 (ego posn: 52 188))
					(2 (ego posn: 135 188))
					(3 (ego posn: 212 188))
				)
			)
			((== prevRoomNum 240) (ego posn: 317 135 loop: 1))
			((== prevRoomNum 235) (ego posn: 100 128 loop: 0))
			((== prevRoomNum 330)
				(= gCurRoomNum 0)
				(HandsOff)
				(aDoorman posn: 149 53 setCycle: Walk)
				(ego ignoreActors: illegalBits: 0 posn: 179 48 loop: 2)
				(aDoor setCel: 255)
				(DoormanScript changeState: 23)
			)
			(else (ego posn: 212 188))
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0002) (curRoom newRoom: 235))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(Ok)
				(ego setMotion: MoveTo 201 123 self)
			)
			(2
				(ego
					view: 231
					setLoop: (if (ego loop?) 1 else 0)
					setCel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(ego setLoop: (+ (ego loop?) 2) setCycle: Fwd)
				(= cycles 20)
			)
			(4
				(Print 230 16 #at -1 10 #icon 21 0 0)
				(= cycles 20)
			)
			(5
				(ego get: 4 setLoop: (+ (ego loop?) 2) setCycle: End)
				(= cycles 10)
			)
			(6
				(NormalEgo (- (ego loop?) 4))
				(theGame changeScore: 20)
				(Print 230 17 #at -1 10)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(cond 
			((Said 'drag,break,get,break/blade')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					(musicLoop (Print 230 0))
					((not (InRoom 4)) (AlreadyTook))
					((not (& (ego onControl:) $0010)) (NotClose))
					(else (Print 230 0))
				)
			)
			((Said 'carve/blade>')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					(musicLoop (Print 230 0))
					((not (InRoom 4)) (AlreadyTook))
					((not (& (ego onControl:) $0010)) (NotClose))
					(
					(or (not (Said '//ginsu')) (not (ego has: 2))) (Print 230 1))
					((!= ((Inv at: 2) view?) 21) (Print 230 2))
					(else (self changeState: 1))
				)
				(event claimed: 1)
			)
			((Said 'use/ginsu>')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (ego has: 2)) (DontHave))
					((not (InRoom 4)) (AlreadyTook))
					((not (& (ego onControl:) $0010)) (NotClose))
					((not (Said '//blade<carve')) (Print 230 3))
					((!= ((Inv at: 2) view?) 21) (Print 230 2))
					(else (self changeState: 1))
				)
				(event claimed: 1)
			)
			((Said 'look>')
				(cond 
					((Said '/door,awning,club')
						(Print 230 4 #mode 1)
						(if (not musicLoop)
							(Print 230 5)
						else
							(Print 230 6 #mode 1)
						)
					)
					((Said '/boulder,cliff') (Print 230 7))
					((and (InRoom 4) (Said '/blade')) (Print 230 8) (Print 230 9 #at -1 144))
					((Said '/flower') (Print 230 10))
					((Said '/palm') (Print 230 11) (Print 230 12 #at -1 144))
					((Said '[/area]')
						(if (cast contains: aDoorman)
							(Print 230 13)
						else
							(Print 230 14)
							(Print 230 15)
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
						(> (event x?) 164)
						(< (event x?) 248)
						(> (event y?) 95)
						(< (event y?) 128)
					)
					(event claimed: 1)
					(switch theCursor
						(2
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (ego has: 2)) (DontHave))
								((not (InRoom 4)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (NotClose))
								((!= ((Inv at: 2) view?) 21) (Print 230 2))
								(else (self changeState: 1))
							)
							(event claimed: 1)
						)
						(995
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								(musicLoop (Print 230 0))
								((not (InRoom 4)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (NotClose))
								(
								(or (not (Said '//ginsu')) (not (ego has: 2))) (Print 230 1))
								((!= ((Inv at: 2) view?) 21) (Print 230 2))
								(else (self changeState: 1))
							)
							(event claimed: 1)
						)
						(998
							(if (cast contains: aDoorman)
								(Print 230 13)
							else
								(Print 230 14)
								(Print 230 15)
							)
						)
						(21
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (ego has: 2)) (DontHave))
								((not (InRoom 4)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (NotClose))
								((!= ((Inv at: 2) view?) 21) (Print 230 2))
								(else (self changeState: 1))
							)
							(event claimed: 1)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 90)
						(< (event x?) 230)
						(> (event y?) 174)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 136 192)
						)
						(998 (Print {OK}))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 306)
						(< (event x?) 319)
						(> (event y?) 96)
						(< (event y?) 162)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 321 127)
						)
						(998 (Print {OK}))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 94)
						(< (event x?) 118)
						(> (event y?) 69)
						(< (event y?) 133)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 50 138 self)
							(ego setMotion: MoveTo 45 138)
						)
						(998 (Print 230 7))
						(995 (curRoom newRoom: 235))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(proc0_26 aDoorman (event x?) (event y?))
						(cast contains: aDoorman)
					)
					(event claimed: 1)
					(switch theCursor
						(994
							(cond 
								((not (& (ego onControl:) $0020)) (NotClose))
								((Btst 36) (Print 230 20))
								((not confirmBJ) (= confirmBJ 1) (Print 230 21))
								((< global88 3) (Print 230 22) (Print 230 23 #at -1 144))
								(else (DoormanScript changeState: 11))
							)
						)
						(996
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (& (ego onControl:) $0020)) (Print 230 31))
								((Btst 36) (Print 230 32))
								(else
									(Print 230 33)
									(Format @str 230 34)
									(DoormanScript changeState: 1)
								)
							)
						)
						(998
							(Print 230 35)
							(Print 230 36 #at -1 144)
						)
						(30
							(Print 230 39)
							(Format @str 230 40)
							(DoormanScript changeState: 1)
						)
						(14
							(Format @str 230 41)
							(DoormanScript changeState: 1)
						)
						(15
							(Format @str 230 41)
							(DoormanScript changeState: 1)
						)
						(16
							(Format @str 230 41)
							(DoormanScript changeState: 1)
						)
						(17
							(Format @str 230 41)
							(DoormanScript changeState: 1)
						)
						(18 (Print 230 42))
						(25
							(if (Btst 36)
								(Print 230 20)
							else
								(DoormanScript changeState: 3)
								(= gTheCursor 900)
								(theGame setCursor: 998 (HaveMouse))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aDoor (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(994 (event claimed: 1))
						(996 (event claimed: 1))
						(998
							(if musicLoop (Print 230 18) else (Print 230 19))
						)
						(995 (event claimed: 1))
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aDoor of Prop
	(properties
		y 50
		x 179
		view 230
	)
	
	(method (init)
		(super init:)
		(self setPri: 1 stopUpd:)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look/door')
			(if musicLoop (Print 230 18) else (Print 230 19))
		)
	)
)

(instance aSign of Prop
	(properties
		x 179
		view 230
		loop 1
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 5 setCycle: Fwd)
	)
)

(instance atpSign of PV
	(properties
		x 179
		view 230
		loop 2
		priority 4
		signal $4000
	)
)

(instance aDoorman of Act
	(properties
		y 53
		x 179
		view 422
		loop 2
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: DoormanScript setPri: 1 stopUpd:)
	)
)

(instance DoormanScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(aDoorman setLoop: 2 setCycle: Fwd)
				(= seconds 3)
			)
			(2
				(aDoorman setCel: 0 stopUpd:)
				(if (not str) (Format @str 230 44))
				(localproc_0026 422 3 0)
				(HandsOn)
			)
			(3
				(HandsOff)
				(theGame changeScore: 43)
				(Print 230 45 #icon 25 0 0)
				(= global94 0)
				(PutInRoom 6)
				(aDoorman setLoop: 2 setCycle: Fwd)
				(= cycles 0)
				(= seconds 3)
			)
			(4
				(aDoorman setCel: 0 stopUpd:)
				(Format @str 230 46)
				(localproc_0026 422 3 1)
				(= seconds 2)
			)
			(5
				(User canControl: 1)
				(aDoorman
					illegalBits: 0
					setLoop: 1
					setCycle: Fwd
					setMotion: MoveTo 149 53 self
				)
				(aDoor setCycle: End)
			)
			(6
				(HandsOff)
				(aDoorman setLoop: 2 setCel: 0)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 179 57 self
				)
			)
			(7
				(ego setMotion: MoveTo 179 48 self setPri: 0)
				(if (Btst 65) (Print 230 47))
			)
			(8 (aDoor setCycle: Beg self))
			(9
				(orchidSeconds number: 11 loop: 1 play:)
				(= cycles 5)
			)
			(10 (curRoom newRoom: 330))
			(11
				(HandsOff)
				(Print 230 48)
				(aDoorman setLoop: 2 setCel: 0)
				(= cycles 0)
				(= seconds 3)
			)
			(12
				(Format @str 230 49)
				(localproc_0026 422 3 1)
				(= seconds 3)
			)
			(13
				(Format @str 230 50)
				(localproc_0026 422 3 6)
				(= seconds 3)
			)
			(14
				(User canControl: 1)
				(aDoorman
					illegalBits: 0
					setLoop: 1
					setCycle: Fwd
					setMotion: MoveTo 144 53 self
				)
			)
			(15
				(HandsOff)
				(aDoorman setLoop: 2 setCel: 0 stopUpd:)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 144 57 self
				)
			)
			(16
				(ego
					setLoop: 3
					setCel: 0
					setPri:
					setMotion: MoveTo 144 77 self
				)
			)
			(17 (= seconds 3))
			(18
				(BJicon view: 422 loop: 3)
				(Print
					230
					51
					#at
					-1
					10
					#title
					{the Doorman says}
					#mode
					1
					#icon
					BJicon
				)
				(= seconds 3)
			)
			(19
				(ego setMotion: MoveTo 144 57 self)
			)
			(20
				(Print 230 52)
				(= seconds 3)
			)
			(21
				(Format @str 230 53)
				(localproc_0026 422 3 9)
				(aDoorman
					setLoop: -1
					setCel: -1
					setCycle: Walk
					setMotion: MoveTo 179 53 self
				)
			)
			(22
				(Bset 65)
				(Print 230 54)
				(ego setLoop: -1 setCel: -1 setPri: -1 setCycle: Walk)
				(= cycles 12)
				(= state 4)
			)
			(23
				(ego setMotion: MoveTo 186 60 self)
			)
			(24 (aDoor setCycle: Beg self))
			(25
				(orchidSeconds number: 11 loop: 1 play:)
				(aDoor stopUpd:)
				(aDoorman setMotion: MoveTo 179 53 self)
			)
			(26
				(aDoorman loop: 2 setCycle: Fwd)
				(= seconds 3)
			)
			(27
				(aDoorman setCel: 0 stopUpd:)
				(NormalEgo)
				(Print 230 55)
			)
		)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'give/job<blow')
					(Said 'give/blow<job')
					(Said 'give/head')
					(Said 'affirmative')
					(Said 'give/head<job')
					(Said 'eat,blow,eat,eat/man,ball,coconut,ball')
				)
				(cond 
					((not (& (ego onControl:) $0020)) (NotClose))
					((Btst 36) (Print 230 20))
					((not confirmBJ) (= confirmBJ 1) (Print 230 21))
					((< global88 3) (Print 230 22) (Print 230 23 #at -1 144))
					(else (self changeState: 11))
				)
			)
			(
				(or
					(Said 'board/club,area,backstage')
					(Said 'ask/dale,chip,show')
					(Said 'look/show')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0020)) (Print 230 24))
					(else
						(Printf 230 25)
						(Format @str 230 26)
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said 'bracelet,give,show/pass/man')
					(Said 'bracelet,give,show/man/pass')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0020)) (Print 230 27))
					(else
						(Print 230 28)
						(Format @str 230 29)
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said 'bracelet,give,show/buck,bill/man')
					(Said 'buy,tip/man')
					(Said 'bracelet,give,show/man/buck,bill')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((Btst 36) (Print 230 20))
					((not (ego has: 6)) (Print 230 30))
					((not (& (ego onControl:) $0020)) (Print 230 27))
					(else (self changeState: 3))
				)
			)
			((Said 'address/man')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0020)) (Print 230 31))
					((Btst 36) (Print 230 32))
					(else
						(Print 230 33)
						(Format @str 230 34)
						(self changeState: 1)
					)
				)
			)
			((Said 'look/man') (Print 230 35) (Print 230 36 #at -1 144))
			((Said 'give>')
				(= inventorySaidMe (inventory saidMe:))
				(event claimed: 0)
				(cond 
					((not (& (ego onControl:) $0020)) (NotClose))
					((Said '[/!*]') (Print 230 37))
					((not inventorySaidMe) (Print 230 38))
					((not (inventorySaidMe ownedBy: ego)) (DontHave))
					((== inventorySaidMe (inventory at: 12))
						(Print 230 39)
						(Format @str 230 40)
						(self changeState: 1)
					)
					(
						(or
							(== inventorySaidMe (inventory at: 14))
							(== inventorySaidMe (inventory at: 15))
							(== inventorySaidMe (inventory at: 16))
							(== inventorySaidMe (inventory at: 17))
						)
						(Format @str 230 41)
						(self changeState: 1)
					)
					((== inventorySaidMe (inventory at: 18)) (Print 230 42))
					((not (== inventorySaidMe (inventory at: 6))) (Print 230 43))
					(else (self changeState: 3))
				)
				(event claimed: 1)
			)
		)
	)
)

(instance BJicon of DCIcon
	(properties)
	
	(method (init)
		(super init:)
		(if cycler (cycler dispose:))
		((= cycler (End new:)) init: self)
	)
)

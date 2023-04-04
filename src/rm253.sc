;;; Sierra Script 1.0 - (do not remove this comment)
(script# 253)
(include sci.sh)
(use Main)
(use AutoDoor)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm253 0
)
(synonyms
	(barrel can barrel)
	(basin handle faucet fountain)
)

(local
	egoX
	egoY
)
(instance rm253 of Rm
	(properties
		picture 253
		west 250
	)
	
	(method (init)
		(Load rsVIEW 254)
		(if musicLoop
			(= egoX 45)
			(= egoY 139)
		else
			(= egoX 43)
			(= egoY 137)
		)
		(if
		(and (ego has: 4) (== ((Inv at: 4) view?) 23))
			(Load rsVIEW 707)
			(Load rsVIEW 701)
			(Load rsVIEW 23)
		)
		(if (ego has: 13) (Load rsVIEW 13))
		(if (not (Btst 30)) (aCredit1 init:) (aCredit2 init:))
		(if
			(or
				(Btst 15)
				(and
					(not (Random 0 4))
					(> global87 16)
					(not musicLoop)
					(not (Btst 64))
					(Btst 30)
				)
			)
			(aJodi init:)
			(aBill init:)
			(alsHead init:)
			(alsFeet init:)
		)
		(super init:)
		(self setScript: RoomScript)
		(if (InRoom 5) (aSoap init:))
		(aDoor setPri: 10 ignoreActors: init:)
		(ego posn: 1 171)
		(NormalEgo)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(& (ego onControl:) $0002)
					(or (< (ego heading?) 90) (> (ego heading?) 269))
				)
				(aDoor doorCtrl: 2 loop: 0)
			)
			(
				(and
					(& (ego onControl:) $0010)
					(> (ego heading?) 89)
					(< (ego heading?) 271)
				)
				(aDoor doorCtrl: 16 loop: 1)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(Ok)
				(ego illegalBits: 0 setMotion: MoveTo egoX egoY self)
			)
			(2
				(ego view: 254 loop: 2 cel: 0 setCycle: End self)
			)
			(3
				(aSoap dispose:)
				(ego get: 5 loop: 3 cel: 0 setCycle: End self)
			)
			(4
				(NormalEgo 0)
				(theGame changeScore: 12)
				(Print 253 31)
			)
			(5
				(HandsOff)
				(Ok)
				(ego illegalBits: 0 setMotion: MoveTo egoX egoY self)
			)
			(6
				(orchidSeconds number: 253 loop: 11 play:)
				(ego
					view: (+ 254 musicLoop)
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(7
				(ego loop: 1 setCycle: Fwd)
				(= seconds 5)
			)
			(8
				(ego loop: 0 setCel: 255 setCycle: Beg self)
			)
			(9
				(orchidSeconds stop:)
				(if (not (Btst 7)) (Bset 7) (theGame changeScore: 2))
				(Print 253 32 #at -1 10)
				(NormalEgo 0)
			)
			(10
				(HandsOff)
				(Ok)
				(ego illegalBits: 0 setMotion: MoveTo egoX egoY self)
			)
			(11
				(orchidSeconds number: 253 loop: 1 play:)
				(ego
					view: (+ 254 musicLoop)
					loop: 2
					cel: 0
					setCycle: End self
				)
				(Print 253 33 #icon 13 0 0)
			)
			(12
				(ego loop: 3 setCycle: Fwd)
				(= seconds 5)
			)
			(13
				(ego loop: 2 setCel: 255 setCycle: Beg self)
			)
			(14
				(orchidSeconds stop:)
				((Inv at: 13) view: 29)
				(Format ((Inv at: 13) name?) 253 34)
				(if (not (Btst 42))
					(Bset 42)
					(theGame changeScore: 37)
				)
				(NormalEgo 0)
			)
			(15
				(HandsOff)
				(Ok)
				(if (not (Btst 59))
					(Bset 59)
					(theGame changeScore: 10)
				)
				(ego
					illegalBits: 0
					cycleSpeed: 1
					view: 701
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(16
				(= global66 707)
				(= gCurRoomNum 10)
				(NormalEgo 3)
				(Print 253 35 #icon 23 0 0)
			)
			(17
				(Ok)
				(HandsOff)
				(ego
					illegalBits: 0
					cycleSpeed: 1
					view: 701
					loop: 2
					setCel: 255
					setCycle: Beg self
				)
			)
			(18
				(= global66 700)
				(= gCurRoomNum 0)
				(NormalEgo 3)
				(if (ego has: 6)
					(PutInRoom 4 -1)
					(Print 253 36)
					(= gTheCursor 900)
					(theGame setCursor: 998 (HaveMouse))
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			(
				(or
					(Said 'drink')
					(Said 'drag/basin,water')
					(Said 'get/drink/water')
					(Said 'get/drink')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0080)) (Print 253 0))
					(else (self changeState: 5))
				)
			)
			((Said 'leak')
				(if (not (& (ego onControl:) $0010))
					(Print 253 1)
				else
					(Print 253 2)
				)
			)
			(
				(or
					(Said 'fill/bottle')
					(Said 'backdrop/water/bottle')
					(Said 'get/water')
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0080)) (Print 253 0))
					((not (ego has: 13)) (Print 253 3))
					((!= ((Inv at: 13) view?) 28) (Print 253 4))
					(else (self changeState: 10))
				)
			)
			((Said 'get/soap')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (InRoom 5)) (AlreadyTook))
					((not (& (ego onControl:) $0080)) (NotClose))
					(else (self changeState: 1))
				)
			)
			(
				(or
					(Said 'wear,(alter<in),(backdrop<on)/blade,skirt')
					(Said '(alter<from,out),(get<off),drain/cloth,cloth')
				)
				(cond 
					((& (ego onControl:) $0040) (Print 253 5))
					((not (& (ego onControl:) $0010)) (Print 253 1))
					((not (ego has: 4)) (Print 253 6))
					((!= ((Inv at: 4) view?) 23) (Print 253 7))
					((and (< global88 3) (aDoor cel?)) (Print 253 8))
					((== gCurRoomNum 10) (self changeState: 17))
					(else (self changeState: 15))
				)
			)
			(
				(or
					(Said 'wear,(alter<in),(backdrop<on)/cloth,cloth')
					(Said '(alter<from,out),(get<off),drain/blade,skirt')
				)
				(cond 
					((== gCurRoomNum 0) (Print 253 6))
					((& (ego onControl:) $0040) (Print 253 5))
					((not (& (ego onControl:) $0010)) (Print 253 1))
					((!= gCurRoomNum 10) (Print 253 9))
					((and (< global88 3) (aDoor cel?)) (Print 253 8))
					(else (self changeState: 17))
				)
			)
			((Said 'clean/eye,body,i,bracelet')
				(if (or (Btst 8) (Btst 10))
					(Print 253 10)
				else
					(Print 253 11)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/bathroom,building') (Print 253 12))
					((Said '/basin')
						(Printf
							253
							13
							(if (InRoom 5)
								{ A bar of soap hangs over the sink, suspended by a rope looped over a nail.}
							else
								{}
							)
						)
					)
					((and (InRoom 5) (Said '/soap,hemp')) (Print 253 14))
					((and (not (ego has: 13)) (Said '/water')) (Print 253 15))
					((Said '/wall,clovis')
						(if (& (ego onControl:) $0010)
							(Print 253 16)
						else
							(Print 253 17)
						)
					)
					((Said '/clovis') (Print 253 18))
					((Said '/nail,board,hemp') (Print 253 19))
					((Said '/barstool,hole')
						(if (& (ego onControl:) $0010)
							(Print 253 20)
						else
							(NotClose)
						)
					)
					((Said '/barrel')
						(if (& (ego onControl:) $0100)
							(Print 253 21)
							(Print 253 22)
						else
							(NotClose)
						)
					)
					((Said '/backstage,casino') (Print 253 23))
					((Said '/door')
						(cond 
							((& (ego onControl:) $0010) (Print 253 24))
							((& (ego onControl:) $0002) (Print 253 25))
							((& (ego onControl:) $0004) (Print 253 26))
							((& (ego onControl:) $0008) (Print 253 27))
							(else (NotClose))
						)
					)
					((Said '[/area]') (Print 253 28))
				)
			)
			((Said '/door') (Print 253 29))
			((Said '/barrel') (Print 253 30))
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if (proc0_26 aDoor (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((& (ego onControl:) $0010) (Print 253 24))
								((& (ego onControl:) $0002) (Print 253 25))
								((& (ego onControl:) $0004) (Print 253 26))
								((& (ego onControl:) $0008) (Print 253 27))
								(else (NotClose))
							)
						)
						(994
							(cond 
								((& (ego onControl:) $0040) (Print 253 5))
								((not (& (ego onControl:) $0010)) (Print 253 1))
								((not (ego has: 4)) (Print 253 6))
								((!= ((Inv at: 4) view?) 23) (Print 253 7))
								((and (< global88 3) (aDoor cel?)) (Print 253 8))
								((== gCurRoomNum 10) (self changeState: 17))
								(else (self changeState: 15))
							)
						)
						(4
							(cond 
								((& (ego onControl:) $0040) (Print 253 5))
								((not (& (ego onControl:) $0010)) (Print 253 1))
								((not (ego has: 4)) (Print 253 6))
								((!= ((Inv at: 4) view?) 23) (Print 253 7))
								((and (< global88 3) (aDoor cel?)) (Print 253 8))
								((== gCurRoomNum 10) (self changeState: 17))
								(else (self changeState: 15))
							)
						)
						(23
							(cond 
								((& (ego onControl:) $0040) (Print 253 5))
								((not (& (ego onControl:) $0010)) (Print 253 1))
								((not (ego has: 4)) (Print 253 6))
								((!= ((Inv at: 4) view?) 23) (Print 253 7))
								((and (< global88 3) (aDoor cel?)) (Print 253 8))
								((== gCurRoomNum 10) (self changeState: 17))
								(else (self changeState: 15))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 ego (event x?) (event y?))
					(if (== theCursor 994)
						(event claimed: 1)
						(cond 
							((& (ego onControl:) $0040) (Print 253 5))
							((not (& (ego onControl:) $0010)) (Print 253 1))
							((not (ego has: 4)) (Print 253 6))
							((!= ((Inv at: 4) view?) 23) (Print 253 7))
							((and (< global88 3) (aDoor cel?)) (Print 253 8))
							((== gCurRoomNum 10) (self changeState: 17))
							(else (self changeState: 15))
						)
					)
					(if (== theCursor 23)
						(event claimed: 1)
						(cond 
							((& (ego onControl:) $0040) (Print 253 5))
							((not (& (ego onControl:) $0010)) (Print 253 1))
							((not (ego has: 4)) (Print 253 6))
							((!= ((Inv at: 4) view?) 23) (Print 253 7))
							((and (< global88 3) (aDoor cel?)) (Print 253 8))
							((== gCurRoomNum 10) (self changeState: 17))
							(else (self changeState: 15))
						)
					)
				else
					(event claimed: 0)
				)
				(if (proc0_26 aJodi (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((< (aJodi y?) 0) (Print 253 45))
								((and (> state 3) (< state 6)) (Print 253 46))
								(else (Print 253 47))
							)
						)
						(996 (Print 253 44 #at -1 144))
						(else  (event claimed: 0))
					)
				)
				(if
					(or
						(proc0_26 alsHead (event x?) (event y?))
						(proc0_26 alsFeet (event x?) (event y?))
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 253 39))
						(996 (Print 253 38))
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aBill (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998 (Print 253 40))
						(996 (Print 253 37))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 1)
						(< (event x?) 10)
						(> (event y?) 148)
						(< (event y?) 179)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo -4 169)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 47)
						(< (event x?) 319)
						(> (event y?) 143)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 253 28))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 236)
						(< (event x?) 251)
						(> (event y?) 110)
						(< (event y?) 128)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(if
								(if (& (ego onControl:) $0100)
									(Print 253 21)
									(Print 253 22)
								)
							else
								(NotClose)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 48)
						(< (event x?) 64)
						(> (event y?) 104)
						(< (event y?) 134)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (InRoom 5)) (AlreadyTook))
								((not (& (ego onControl:) $0080)) (NotClose))
								(else (self changeState: 1))
							)
						)
						(996
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (& (ego onControl:) $0080)) (Print 253 0))
								(else (self changeState: 5))
							)
						)
						(998
							(if (and (InRoom 5) (Print 253 14)) 1)
						)
						(28
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (& (ego onControl:) $0080)) (Print 253 0))
								((!= ((Inv at: 13) view?) 28) (Print 253 4))
								(else (RoomScript changeState: 10))
							)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aDoor of AutoDoor
	(properties
		y 126
		x 93
		view 253
		roomCtrl 0
	)
)

(instance aSoap of View
	(properties
		y 111
		x 64
		view 253
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 ignoreActors: stopUpd:)
	)
)

(instance BillScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aBill setCel: 0)
				(= seconds (Random 2 9))
			)
			(1
				(aBill setLoop: 5 setCycle: Fwd)
				(= state -1)
				(= seconds (Random 2 5))
			)
			(2
				(aBill setLoop: 6 cel: 0 setCycle: End self)
				(= seconds 0)
			)
			(3
				(Print 253 42)
				(Print 253 43)
				(= seconds 3)
			)
			(4
				(aBill setCycle: Beg self)
				(= state -1)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'address/bill,man') (Print 253 37))
			((Said 'address/al') (Print 253 38))
			((Said 'look/al') (Print 253 39))
			((Said 'look/bill') (Print 253 40))
			((Said 'look/man') (Print 253 41))
		)
	)
)

(instance aJodi of Act
	(properties
		y 143
		x -30
		view 256
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setCycle: Walk setScript: JodiScript)
	)
)

(instance JodiScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1
				(aJodi setMotion: MoveTo 184 143 self)
			)
			(2
				(aJodi setMotion: MoveTo 184 137 self)
			)
			(3 (= seconds 3))
			(4
				(aJodi loop: 4 cel: 0 cycleSpeed: 2 setCycle: End self)
			)
			(5
				(Bset 64)
				(if
					(or
						(& (ego onControl:) $0040)
						(& (ego onControl:) $0008)
					)
					(Print 253 48)
				)
				(= seconds 3)
			)
			(6
				(aJodi
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 184 143 self
				)
			)
			(7
				(aJodi setMotion: MoveTo (aBill x?) 143 self)
			)
			(8
				(BillScript changeState: 2)
				(aJodi setMotion: MoveTo -10 143 self)
			)
			(9
				(aJodi dispose:)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/body') (Print 253 44 #at -1 144))
			((Said '/babe,blond')
				(cond 
					((< (aJodi y?) 0) (Print 253 45))
					((and (> state 3) (< state 6)) (Print 253 46))
					(else (Print 253 47))
				)
			)
		)
	)
)

(instance aBill of Prop
	(properties
		y 140
		x 160
		view 253
		loop 5
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 setScript: BillScript stopUpd:)
	)
)

(instance alsHead of View
	(properties
		y 96
		x 138
		view 253
		loop 3
		priority 6
		signal $4101
	)
)

(instance alsFeet of View
	(properties
		y 132
		x 138
		view 253
		loop 4
		priority 9
		signal $4101
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 240
		view 257
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
		view 257
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
				(Bset 30)
				(aCredit1 setCycle: Beg)
				(aCredit2 setCycle: Beg self)
			)
			(4
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)

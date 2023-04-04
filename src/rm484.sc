;;; Sierra Script 1.0 - (do not remove this comment)
(script# 484)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm484 0
)

(local
	leaveMsg
	[str 200]
)
(instance aWine of View
	(properties
		y 115
		x 162
		view 480
		loop 4
		cel 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 setCel: 3 ignoreActors: stopUpd:)
	)
)

(instance aDoor of Prop
	(properties
		y 65
		x 159
		view 480
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 3 ignoreActors: stopUpd:)
	)
)

(instance aPanties of View
	(properties
		y 131
		x 63
		view 480
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aBra of View
	(properties
		y 126
		x 63
		view 480
		loop 1
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aPantyhose of View
	(properties
		y 141
		x 63
		view 480
		loop 1
		cel 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aDress of Prop
	(properties
		y 107
		x 39
		view 480
		loop 2
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 11
			setCel: (if (InRoom 17 484) 0 else 255)
			ignoreActors:
			stopUpd:
		)
	)
)

(instance atpTelescope of PV
	(properties
		y 50
		x 160
		view 480
		loop 3
		priority 1
		signal $4000
	)
)

(instance rm484 of Rm
	(properties
		picture 480
		south 470
	)
	
	(method (init)
		(super init:)
		(User canInput: 0 mapKeyToDir: 0)
		(aWine init: setCel: (if (InRoom 13) 1 else 0))
		(aDoor init: setCel: 255 stopUpd:)
		(if (InRoom 14) (aPanties init:))
		(if (InRoom 16) (aBra init:))
		(if (InRoom 15) (aPantyhose init:))
		(aDress init:)
		(addToPics add: atpTelescope doit:)
		(self setScript: RoomScript)
		(NormalEgo)
		(= gCurRoomNum 0)
		(if (== prevRoomNum 470)
			(ego posn: 159 188 loop: 3 init:)
		else
			(if (or (Btst 69) (== prevRoomNum 0))
				(Bclr 69)
				(systemWindow color: gameHours back: oldSysTime)
				(TheMenuBar draw: state: 1)
				(SL enable:)
				(Bclr 4)
				(Bclr 5)
			)
			(= global66 800)
			(= musicLoop 1)
			(= global82 (Format @global83 484 0))
			(= global98 4)
			(PutInRoom 6 450)
			((Inv at: 6) view: 25)
			(Format ((Inv at: 6) name?) {47 dolars on tips._____})
			(PutInRoom 18 450)
			(PutInRoom 2 -1)
			(PutInRoom 8 -1)
			(PutInRoom 9 -1)
			(ego get: 12)
			((Inv at: 12) view: 30)
			(Format ((Inv at: 12) name?) 484 2)
			(theGame setSpeed: currentStatus)
			(User canInput: 0 mapKeyToDir: 0)
			(Load rsVIEW 800)
			(ego
				view: 482
				posn: 160 59
				loop: 2
				baseSetter: SheetBase
				init:
			)
			(gTheMusic number: 492 loop: global72 play:)
		)
	)
	
	(method (newRoom newRoomNumber)
		(gTheMusic fade:)
		(super newRoom: newRoomNumber)
		(gTheMusic fade:)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0040)
			(if
			(and (not leaveMsg) (!= (ego view?) global66))
				(= leaveMsg 1)
				(Print 484 3)
			)
		else
			(= leaveMsg 0)
		)
		(if (!= (ego view?) 800)
			(ego observeControl: 8192)
		else
			(ego ignoreControl: 8192)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(Ok)
				(ego get: 17 view: 800)
				(theGame changeScore: 10)
				(Print 484 36)
				(aDress setCycle: End self)
				(++ state)
			)
			(2
				(Ok)
				(PutInRoom 17)
				(ego view: 482)
				(theGame changeScore: -10)
				(Print 484 37)
				(aDress setCycle: Beg self)
			)
			(3 (aDress stopUpd:))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'drain,(get<off)/dress')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (ego has: 17)) (DontHave))
					(
					(and (ego has: 16) (ego has: 14) (ego has: 15)) (Print 484 4))
					((not (& (ego onControl:) $0010)) (Print 484 5))
					(else (self changeState: 2))
				)
			)
			(
				(Said
					'wear,drain,(on<backdrop),(alter<in),(backdrop<on),get>'
				)
				(cond 
					((!= gCurRoomNum 0) (GoodIdea) (event claimed: 1))
					((Said '/sheet')
						(if (== (ego view?) 482)
							(Print 484 6)
						else
							(Print 484 7)
						)
					)
					((Said '/beer,bottle')
						(cond 
							((not (InRoom 13)) (AlreadyTook))
							((not (ego inRect: 126 126 196 141)) (NotClose))
							(else
								(Ok)
								(aWine setCel: 0 stopUpd:)
								(theGame changeScore: 25)
								(ego get: 13)
							)
						)
					)
					((Said '/panties')
						(cond 
							((not (InRoom 14)) (AlreadyTook))
							((not (& (ego onControl:) $0010)) (Print 484 5))
							(else
								(Ok)
								(ego get: 14)
								(theGame changeScore: 20)
								(aPanties dispose:)
								(Print 484 8)
							)
						)
					)
					((Said '/bra')
						(cond 
							((not (InRoom 16)) (AlreadyTook))
							((not (& (ego onControl:) $0010)) (Print 484 5))
							((ego has: 16) (Print 484 9))
							(else
								(Ok)
								(ego get: 16)
								(theGame changeScore: 20)
								(aBra dispose:)
								(Print 484 10)
							)
						)
					)
					((Said '/hose')
						(cond 
							((!= gCurRoomNum 0) (GoodIdea))
							((not (InRoom 15)) (AlreadyTook))
							((not (& (ego onControl:) $0010)) (Print 484 5))
							(else
								(Ok)
								(ego get: 15)
								(theGame changeScore: 20)
								(aPantyhose dispose:)
								(Print 484 11)
							)
						)
					)
					((Said '/dress')
						(cond 
							((!= gCurRoomNum 0) (GoodIdea))
							((not (InRoom 17)) (AlreadyTook))
							((not (& (ego onControl:) $0010)) (Print 484 5))
							(else (self changeState: 1))
						)
					)
				)
			)
			((Said 'look<below') (Print 484 12))
			((Said 'naked,naked') (Print 484 13))
			((Said 'backdrop,decrease,drain,(get<off)/sheet') (Print 484 14))
			((Said '/sandal') (Print 484 15))
			((Said '/tray') (Print 484 16))
			((Said 'gamble/keyboard') (Print 484 17))
			((Said 'close/door') (Print 484 18))
			((Said 'look>')
				(cond 
					((Said '/lotion,(buffet<dressing),cloth')
						(cond 
							((InRoom 15) (Print 484 19))
							((InRoom 14) (Print 484 20))
							((InRoom 16) (Print 484 21))
							(else (Print 484 22))
						)
						(if (InRoom 17) (Print 484 23))
					)
					((Said '/buffet')
						(if (InRoom 13) (Print 484 24) else (Print 484 25))
						(if (or (InRoom 15) (InRoom 14) (InRoom 16))
							(Print 484 26)
						else
							(Print 484 22)
						)
					)
					((Said '/tray') (if (InRoom 13) (Print 484 27) else (Print 484 25)))
					((and (InRoom 13) (Said '/bottle')) (Print 484 27))
					((Said '/bed') (Print 484 28))
					((Said '/keyboard') (Print 484 29))
					((Said '/binocular') (Print 484 30))
					((Said '/burn') (Print 484 31))
					((Said '/bush') (Print 484 32))
					((Said '/balcony,look,cup') (Print 484 33))
					((Said '[/area]') (Print 484 34))
				)
			)
			((Said '/cloth') (Print 484 35))
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 37)
						(< (event x?) 47)
						(> (event y?) 105)
						(< (event y?) 137)
						(cast contains: aDress)
					)
					(event claimed: 1)
					(switch theCursor
						(994
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (InRoom 17)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (Print 484 5))
								(else (self changeState: 1))
							)
						)
						(995
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (InRoom 17)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (Print 484 5))
								(else (self changeState: 1))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 138)
						(< (event x?) 180)
						(> (event y?) 180)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 158 192)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(proc0_26 aBra (event x?) (event y?))
						(cast contains: aBra)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								((not (InRoom 16)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (Print 484 5))
								((ego has: 16) (Print 484 9))
								(else
									(Ok)
									(ego get: 16)
									(theGame changeScore: 20)
									(aBra dispose:)
									(Print 484 10)
								)
							)
						)
						(994
							(cond 
								((not (InRoom 16)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (Print 484 5))
								((ego has: 16) (Print 484 9))
								(else
									(Ok)
									(ego get: 16)
									(theGame changeScore: 20)
									(aBra dispose:)
									(Print 484 10)
								)
							)
						)
						(998
							(if (InRoom 16) (Print 484 21) else (event claimed: 0))
						)
					)
				)
				(if
					(and
						(proc0_26 aPanties (event x?) (event y?))
						(cast contains: aPanties)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								((not (InRoom 14)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (Print 484 5))
								(else
									(Ok)
									(ego get: 14)
									(theGame changeScore: 20)
									(aPanties dispose:)
									(Print 484 8)
								)
							)
						)
						(994
							(cond 
								((not (InRoom 14)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (Print 484 5))
								(else
									(Ok)
									(ego get: 14)
									(theGame changeScore: 20)
									(aPanties dispose:)
									(Print 484 8)
								)
							)
						)
						(998
							(if (InRoom 14) (Print 484 20) else (event claimed: 0))
						)
					)
				)
				(if
					(and
						(proc0_26 aPantyhose (event x?) (event y?))
						(cast contains: aPantyhose)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (InRoom 15)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (Print 484 5))
								(else
									(Ok)
									(ego get: 15)
									(theGame changeScore: 20)
									(aPantyhose dispose:)
									(Print 484 11)
								)
							)
						)
						(994
							(cond 
								((!= gCurRoomNum 0) (GoodIdea))
								((not (InRoom 15)) (AlreadyTook))
								((not (& (ego onControl:) $0010)) (Print 484 5))
								(else
									(Ok)
									(ego get: 15)
									(theGame changeScore: 20)
									(aPantyhose dispose:)
									(Print 484 11)
								)
							)
						)
						(998
							(if (InRoom 15) (Print 484 19))
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 153)
						(< (event x?) 179)
						(> (event y?) 109)
						(< (event y?) 136)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								((not (InRoom 13)) (AlreadyTook))
								((not (ego inRect: 126 126 196 141)) (NotClose))
								(else
									(Ok)
									(aWine setCel: 0 stopUpd:)
									(theGame changeScore: 25)
									(ego get: 13)
								)
							)
						)
						(998
							(if (InRoom 13) (Print 484 27) else (Print 484 25))
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 114)
						(< (event x?) 216)
						(> (event y?) 78)
						(< (event y?) 105)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 484 28))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 271)
						(< (event x?) 319)
						(> (event y?) 100)
						(< (event y?) 154)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 484 29))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(proc0_26 aWine (event x?) (event y?))
						(cast contains: aWine)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								((not (InRoom 13)) (AlreadyTook))
								((not (ego inRect: 126 126 196 141)) (NotClose))
								(else
									(Ok)
									(aWine setCel: 0 stopUpd:)
									(theGame changeScore: 25)
									(ego get: 13)
								)
							)
						)
						(998
							(if (InRoom 13) (Print 484 27) else (Print 484 25))
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance SheetBase of Code
	(properties)
	
	(method (doit)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) 2))
		(ego brLeft: (- (ego x?) 9))
		(ego brRight: (+ (ego x?) 9))
	)
)

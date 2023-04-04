;;; Sierra Script 1.0 - (do not remove this comment)
(script# 375)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm375 0
)

(instance rm375 of Rm
	(properties
		picture 375
		horizon 1
		south 370
	)
	
	(method (init)
		(if (ego has: 5) (Load rsVIEW 5))
		(aWater1 init:)
		(aWater2 init:)
		(aWater3 init:)
		(aWater4 init:)
		(aDrain init:)
		(if (Btst 49)
			(self picture: 376)
			(Load rsPIC 375)
			(Load rsVIEW 376)
			(orchidSeconds number: 375 loop: global72 play:)
		else
			(self picture: 375)
			(Load rsPIC 376)
			(Load rsVIEW 377)
			(Load rsSOUND 375)
			(aWater1 hide:)
			(aWater2 hide:)
			(aWater3 hide:)
			(aWater4 hide:)
			(aDrain hide:)
		)
		(super init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(ego
			view: (cond 
				((Btst 49) 377)
				((>= global88 3) 376)
				(else 378)
			)
			posn: 53 176
			setPri: 11
			setStep: 5 5
			init:
		)
		(User canInput: 0 mapKeyToDir: 0)
	)
	
	(method (newRoom newRoomNumber)
		(orchidSeconds fade:)
		(if (InRoom 8 375) (ego get: 8) (Print 375 0))
		(super newRoom: newRoomNumber)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (& (ego onControl:) $0004) (Btst 49))
			(Bset 50)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'shampoo') (Print 375 1))
			((or (Said 'caress/self') (Said 'jack')) (Ok) (Print 375 2 #at -1 144))
			(
			(Said 'cease,(drag<off)/bath,faucet,channel,handle,water')
				(cond 
					((not (Btst 49)) (ItIs))
					((not (& (ego onControl:) $0002)) (Print 375 3))
					(else
						(Ok)
						(ego view: (if (>= global88 3) 376 else 378))
						(Bclr 49)
						(curRoom drawPic: 375 8)
						(aWater1 hide:)
						(aWater2 hide:)
						(aWater3 hide:)
						(aWater4 hide:)
						(aDrain hide:)
						(orchidSeconds stop:)
					)
				)
			)
			(
				(or
					(Said 'bath,bath')
					(Said 'get/bath')
					(Said 'use,(drag<on)/bath,faucet,channel,handle,water')
				)
				(cond 
					((Btst 49) (ItIs))
					((not (& (ego onControl:) $0002)) (Print 375 3))
					(else
						(Ok)
						(ego view: 377)
						(Bset 49)
						(curRoom drawPic: 376 8)
						(if (> global87 16)
							(aWater1 show:)
							(aWater2 show:)
							(aWater3 show:)
							(aWater4 show:)
						)
						(aDrain show:)
						(orchidSeconds number: 375 loop: global72 play:)
					)
				)
			)
			((Said 'rinse')
				(cond 
					((not (Btst 49)) (Print 375 4))
					((not (& (ego onControl:) $0004)) (Print 375 5))
					(else (Ok) (Print 375 6))
				)
			)
			((or (Said 'clean') (Said 'clean,use/soap'))
				(cond 
					((not (Btst 49)) (Print 375 4))
					((not (& (ego onControl:) $0004)) (Print 375 5))
					((not (ego has: 5)) (Print 375 7) (Print 375 8 #at -1 144))
					(else
						(Ok)
						(Bclr 8)
						(Bclr 10)
						(theGame changeScore: 60)
						(ego put: 5 -1)
						(Print 375 9 #icon 5 0 0)
						(Print 375 10)
						(if (>= global88 3) (Print 375 11))
						(Print 375 12)
					)
				)
			)
			((and (not (ego has: 5)) (Said 'get/soap')) (Print 375 13) (Print 375 14 #at -1 144))
			((Said 'look>')
				(cond 
					((Said '/faucet,handle,channel') (if (Btst 49) (Print 375 15) else (Print 375 16)))
					((Said '/drain')
						(if (Btst 49)
							(Printf 375 17 (if (Random 0 1) {counter-} else {}))
						else
							(Print 375 18)
						)
					)
					((Said '[/carpet,bath,area]') (Print 375 19))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 42)
						(< (event x?) 286)
						(> (event y?) 21)
						(< (event y?) 134)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 375 19))
						(5
							(cond 
								((not (Btst 49)) (Print 375 4))
								((not (& (ego onControl:) $0004)) (Print 375 5))
								((not (ego has: 5)) (Print 375 7) (Print 375 8 #at -1 144))
								(else
									(Ok)
									(Bclr 8)
									(Bclr 10)
									(theGame changeScore: 60)
									(ego put: 5 -1)
									(= gTheCursor 900)
									(theGame setCursor: 998 (HaveMouse))
									(Print 375 9 #icon 5 0 0)
									(Print 375 10)
									(if (>= global88 3) (Print 375 11))
									(Print 375 12)
								)
							)
						)
						(995
							(cond 
								((Btst 49)
									(cond 
										((not (Btst 49)))
										((not (& (ego onControl:) $0002)) (Print 375 3))
										(else
											(Ok)
											(ego view: (if (>= global88 3) 376 else 378))
											(Bclr 49)
											(curRoom drawPic: 375 8)
											(aWater1 hide:)
											(aWater2 hide:)
											(aWater3 hide:)
											(aWater4 hide:)
											(aDrain hide:)
											(orchidSeconds stop:)
										)
									)
								)
								((not (& (ego onControl:) $0002)) (Print 375 3))
								(else
									(Ok)
									(ego view: 377)
									(Bset 49)
									(curRoom drawPic: 376 8)
									(if (> global87 16)
										(aWater1 show:)
										(aWater2 show:)
										(aWater3 show:)
										(aWater4 show:)
									)
									(aDrain show:)
									(orchidSeconds number: 375 loop: global72 play:)
								)
							)
						)
						(994
							(cond 
								((not (Btst 49)) (Print 375 4))
								((not (& (ego onControl:) $0004)) (Print 375 5))
								(else (Ok) (Print 375 6))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 30)
						(< (event x?) 81)
						(> (event y?) 157)
						(< (event y?) 187)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 46 192)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aWater1 of Prop
	(properties
		y 91
		x 148
		view 375
		loop 1
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd isExtra: 1 ignoreActors:)
	)
)

(instance aWater2 of Prop
	(properties
		y 61
		x 178
		view 375
		loop 2
		cel 2
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd isExtra: 1 ignoreActors:)
	)
)

(instance aWater3 of Prop
	(properties
		y 92
		x 170
		view 375
		loop 1
		cel 3
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd isExtra: 1 ignoreActors:)
	)
)

(instance aWater4 of Prop
	(properties
		y 84
		x 128
		view 375
		loop 2
		cel 4
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd isExtra: 1 ignoreActors:)
	)
)

(instance aDrain of Prop
	(properties
		y 91
		x 148
		view 375
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd ignoreActors:)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 470)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm470 0
)

(local
	floorRoomNum
	floorNumber
	local2
)
(instance rm470 of Rm
	(properties
		picture 470
	)
	
	(method (init)
		(if (ego has: 12) (Load rsVIEW 12))
		(Load rsVIEW (+ 715 (* 100 musicLoop)))
		(Load rsSOUND 460)
		(super init:)
		(aBeamFront init:)
		(aBeamRear init:)
		(aDoor init:)
		(aFloor init:)
		(addToPics add: atpPanel doit:)
		(self setScript: RoomScript)
		(NormalEgo 3)
		(ego posn: 161 137 init:)
		(User canInput: 0 mapKeyToDir: 0)
		(if (> prevRoomNum curRoomNum) (= floorNumber 8))
		(aLight setPri: 4 setCel: floorNumber init: stopUpd:)
		(gTheMusic number: 32 loop: global72 play:)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds 12))
			(1
				(Print 470 18)
				(self changeState: 13)
			)
			(2
				(Ok)
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 179 130 self)
			)
			(3
				(ego
					cycleSpeed: 1
					view: (+ 715 (* 100 musicLoop))
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(4
				(if (not (Btst 63))
					(Bset 63)
					(theGame changeScore: 4)
					(Print 470 19 #icon 12 0 0)
				)
				(ego setCycle: Beg self)
			)
			(5
				(NormalEgo 3)
				(= cycles 10)
				(if (== floorRoomNum 460) (= state 8))
			)
			(6
				(aLight setCel: (++ floorNumber))
				(if (>= floorNumber 9)
					(self changeState: 12)
				else
					(aBeamRear setMotion: MoveTo 159 260)
					(aBeamFront setMotion: MoveTo 160 260 self)
				)
			)
			(7
				(aBeamRear posn: 159 -16 setMotion: MoveTo 159 130)
				(aBeamFront posn: 160 -32 setMotion: MoveTo 160 80 self)
			)
			(8
				(aBeamRear posn: 159 48 setMotion: MoveTo 159 128)
				(aBeamFront setMotion: MoveTo 160 160 self)
				(= state 5)
			)
			(9
				(aLight setCel: (-- floorNumber))
				(if (== floorNumber 0)
					(self changeState: 12)
				else
					(aBeamRear setMotion: MoveTo 159 80)
					(aBeamFront setMotion: MoveTo 160 80 self)
				)
			)
			(10
				(aBeamRear posn: 159 104 setMotion: MoveTo 159 -32)
				(aBeamFront setMotion: MoveTo 160 -32 self)
			)
			(11
				(aBeamRear posn: 159 232 setMotion: MoveTo 159 128)
				(aBeamFront posn: 160 260 setMotion: MoveTo 160 160 self)
				(= state 8)
			)
			(12
				(aFloor
					setLoop: (if (== floorRoomNum 460) 4 else 0)
					stopUpd:
				)
				(= seconds 3)
			)
			(13
				(HandsOff)
				(orchidSeconds number: 460 loop: 1 play:)
				(aDoor setCycle: End self)
			)
			(14
				(aDoor stopUpd:)
				(ego illegalBits: 0 setMotion: MoveTo 160 128 self)
			)
			(15
				(ego setMotion: MoveTo 160 124 self)
			)
			(16
				(gTheMusic fade:)
				(if (not floorRoomNum) (= floorRoomNum prevRoomNum))
				(curRoom newRoom: floorRoomNum)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 50])
		(if (event claimed?) (return))
		(cond 
			(
			(or (Said 'use/key') (Said 'drag/button,carpet')) (Print 470 0))
			(
				(or
					(Said '/penthouse,9,9')
					(Said '//penthouse,9,9')
					(Said 'go,drag/penthouse,9,9')
				)
				(cond 
					((not (ego has: 12))
						(Print 470 1)
						(Print (Format @temp0 470 2 global82) #at -1 144)
					)
					(floorRoomNum (Print 470 3))
					((> floorNumber 7) (Print 470 4))
					(else
						(if musicLoop
							(= floorRoomNum 484)
						else
							(= floorRoomNum 480)
						)
						(self changeState: 2)
					)
				)
			)
			(
				(or
					(Said '/carpet,--invalid--,area')
					(Said '//carpet,--invalid--,(carpet<first),area')
					(Said 'go,drag/carpet,--invalid--,area')
				)
				(cond 
					((not (ego has: 12))
						(Print 470 1)
						(Print (Format @temp0 470 2 global82) #at -1 144)
					)
					(floorRoomNum (Print 470 3))
					((== floorNumber 0) (Print 470 4))
					(else (= floorRoomNum 460) (self changeState: 2))
				)
			)
			((Said 'up,down,open,close/door,carpet,elevator') (Print 470 5))
			((Said 'drag,go/button,door,carpet') (Print 470 6))
			((Said 'hear') (if musicLoop (Print 470 7) else (Print 470 8)))
			((Said 'exit,go,exit,done,cease') (Ok) (Print 470 9))
			((and (ego has: 9) (Said '/keycard')) (Print 470 10))
			((Said 'pick,attack,break/bolt') (Print 470 11))
			((Said 'look>')
				(cond 
					((Said '/burn') (Printf 470 12 (+ 1 (aLight cel?))))
					((Said '/handle,button') (Print 470 13))
					((Said '/door') (Printf 470 14 (if (aDoor cel?) {open} else {closed})))
					((Said '/(hole<key),bolt') (Print 470 15))
					((Said '[/area,elevator]') (Print 470 16) (Print 470 17 #at -1 144))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if (proc0_26 aDoor (event x?) (event y?))
					(switch theCursor
						(998
							(event claimed: 1)
							(Printf 470 14 (if (aDoor cel?) {open} else {closed}))
						)
						(995
							(event claimed: 1)
							(Ok)
							(Print 470 9)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 atpPanel (event x?) (event y?))
					(switch theCursor
						(998
							(event claimed: 1)
							(Print 470 13)
						)
						(30
							(event claimed: 1)
							(= local2
								(Print
									{Where do you want to go?}
									#button
									{Penthouse}
									1
									#button
									{Down}
									2
								)
							)
							(switch local2
								(1
									(cond 
										((not (ego has: 12))
											(Print 470 1)
											(Print (Format @temp0 470 2 global82) #at -1 144)
										)
										(floorRoomNum (Print 470 3))
										((> floorNumber 7) (Print 470 4))
										(else
											(if musicLoop
												(= floorRoomNum 484)
											else
												(= floorRoomNum 480)
											)
											(self changeState: 2)
										)
									)
								)
								(2
									(cond 
										((not (ego has: 12))
											(Print 470 1)
											(Print (Format @temp0 470 2 global82) #at -1 144)
										)
										(floorRoomNum (Print 470 3))
										((== floorNumber 0) (Print 470 4))
										(else (= floorRoomNum 460) (self changeState: 2))
									)
								)
							)
						)
						(12
							(event claimed: 1)
							(= local2
								(Print
									{Where do you want to go?}
									#button
									{Penthouse}
									1
									#button
									{Down}
									2
								)
							)
							(switch local2
								(1
									(cond 
										((not (ego has: 12))
											(Print 470 1)
											(Print (Format @temp0 470 2 global82) #at -1 144)
										)
										(floorRoomNum (Print 470 3))
										((> floorNumber 7) (Print 470 4))
										(else
											(if musicLoop
												(= floorRoomNum 484)
											else
												(= floorRoomNum 480)
											)
											(self changeState: 2)
										)
									)
								)
								(2
									(cond 
										((not (ego has: 12))
											(Print 470 1)
											(Print (Format @temp0 470 2 global82) #at -1 144)
										)
										(floorRoomNum (Print 470 3))
										((== floorNumber 0) (Print 470 4))
										(else (= floorRoomNum 460) (self changeState: 2))
									)
								)
							)
						)
						(995
							(event claimed: 1)
							(= local2
								(Print
									{Where do you want to go?}
									#button
									{Penthouse}
									1
									#button
									{Down}
									2
								)
							)
							(switch local2
								(1
									(cond 
										((not (ego has: 12))
											(Print 470 1)
											(Print (Format @temp0 470 2 global82) #at -1 144)
										)
										(floorRoomNum (Print 470 3))
										((> floorNumber 7) (Print 470 4))
										(else
											(if musicLoop
												(= floorRoomNum 484)
											else
												(= floorRoomNum 480)
											)
											(self changeState: 2)
										)
									)
								)
								(2
									(cond 
										((not (ego has: 12))
											(Print 470 1)
											(Print (Format @temp0 470 2 global82) #at -1 144)
										)
										(floorRoomNum (Print 470 3))
										((== floorNumber 0) (Print 470 4))
										(else (= floorRoomNum 460) (self changeState: 2))
									)
								)
							)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance atpPanel of PV
	(properties
		y 110
		x 184
		view 470
		loop 1
		priority 5
	)
)

(instance aLight of Prop
	(properties
		y 62
		x 160
		view 460
		loop 2
	)
)

(instance aBeamFront of Act
	(properties
		y 156
		x 160
		view 470
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			setPri: 15
			setLoop: (if (> global87 16) 3 else 5)
			setStep: 0 8
		)
	)
)

(instance aBeamRear of Act
	(properties
		y 125
		x 160
		view 470
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			setPri: 2
			setLoop: (if (> global87 39) 2 else 5)
			setStep: 0 8
		)
	)
)

(instance aDoor of Prop
	(properties
		y 125
		x 160
		view 460
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setCel: 0 species 6 stopUpd: ignoreActors:)
	)
)

(instance aFloor of Prop
	(properties
		y 125
		x 160
		view 470
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 5
			setLoop: (if (== prevRoomNum 460) 4 else 0) species
			stopUpd:
		)
	)
)

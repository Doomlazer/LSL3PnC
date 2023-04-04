;;; Sierra Script 1.0 - (do not remove this comment)
(script# 550)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm550 0
)

(local
	drownLoop
	[msgBuf 55]
	[titleBuf 22]
	local78
)
(instance rm550 of Rm
	(properties
		picture 550
		horizon 50
		south 540
	)
	
	(method (init)
		(Load rsVIEW 551)
		(Load rsVIEW 552)
		(Load rsVIEW 553)
		(Load rsSOUND 6)
		(super init:)
		(User canInput: 0 canControl: 0 mapKeyToDir: 0)
		(aLog setCycle: Fwd init:)
		(self setScript: RoomScript)
		(if global64
			(= musicLoop 1)
			(= global66 803)
			((Inv at: 17) view: 31)
			(= gCurRoomNum 0)
		)
		(NormalEgo)
		(ego posn: 159 188 loop: 3 init: viewer: PattiViewer)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(Ok)
				(theGame changeScore: 10)
				(HandsOff)
				(= gCurRoomNum 551)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo (+ (aLog x?) 20) (+ (aLog y?) -14) self
				)
			)
			(2
				(aLog hide:)
				(ego
					cycleSpeed: 1
					moveSpeed: 1
					viewer: 0
					posn: (aLog x?) (aLog y?)
					view: 553
					setLoop: 0
					setCycle: Fwd
					setMotion: MoveTo 170 131 self
				)
			)
			(3
				(aLog posn: 170 131 show:)
				(ego
					viewer: PattiViewer
					posn: (+ (aLog x?) 20) (+ (aLog y?) -14)
				)
				(NormalEgo 2 551)
				(= gCurRoomNum 0)
			)
			(4
				(Ok)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo (+ (aLog x?) 20) (+ (aLog y?) -14) self
				)
			)
			(5
				(aLog hide:)
				(ego
					viewer: 0
					cycleSpeed: 1
					posn: (aLog x?) (aLog y?)
					view: 553
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(6
				(ego setLoop: 2 setCycle: Fwd)
				(= gCurRoomNum 550)
				(if (!= (aLog x?) 77)
					(self changeState: 10)
				else
					(= seconds 7)
				)
				(Print 550 20)
				(if (>= global88 3) (Print 550 21 #at -1 144))
			)
			(7
				(Print 550 22)
				(User canInput: 1)
			)
			(8
				(Ok)
				(HandsOff)
				(ego setLoop: 1 setCel: 255 setCycle: Beg self)
			)
			(9
				(= gCurRoomNum 0)
				(aLog show:)
				(NormalEgo 2 551)
				(ego
					viewer: PattiViewer
					posn: (+ (aLog x?) 20) (+ (aLog y?) -14)
				)
			)
			(10
				(theGame changeScore: 20)
				(ego
					cycleSpeed: 0
					setStep: 2 1
					setMotion: MoveTo 278 106 self
				)
				(= cycles 11)
			)
			(11
				(Print 550 23)
				(Print 550 24)
			)
			(12
				(ego setLoop: 3 setMotion: MoveTo 348 73 self)
			)
			(13
				(Print 550 25)
				(curRoom newRoom: 560)
			)
			(14
				(HandsOff)
				(= gCurRoomNum 1003)
				(Print
					(Format @msgBuf 550 26 filthStr)
					#at
					-1
					10
					#dispose
				)
				(= drownLoop 0)
				(ego
					viewer: drowningViewer
					illegalBits: 0
					ignoreActors:
					view: 812
					setLoop: drownLoop
					setCycle: Fwd
					setMotion: MoveTo (if (> (ego x?) 278) (ego x?) else 278) 106 self
				)
				(orchidSeconds number: 6 loop: -1 play:)
			)
			(15
				(cls)
				(= drownLoop 2)
				(ego setMotion: MoveTo 348 73 self)
			)
			(16
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 816
					register: (Format @msgBuf 550 27)
					next: (Format @titleBuf 550 28)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'bang,board,(climb,lie,get<on)/log')
				(cond 
					((!= gCurRoomNum 0) (GoodIdea))
					((not (< (ego distanceTo: aLog) 25)) (NotClose))
					(else (self changeState: 4))
				)
			)
			(
				(Said
					'nightstand,exit,(get<off),(climb,get<off,up),(get<up),(nightstand<up)'
				)
				(if (!= gCurRoomNum 550)
					(Print 550 0)
				else
					(self changeState: 8)
				)
			)
			((Said 'weave') (Print 550 1))
			((Said '/island') (Print 550 2) (Print 550 3 #at -1 144))
			((Said 'attack,attack,grasp,grab/log')
				(cond 
					((not (< (ego distanceTo: aLog) 25)) (NotClose))
					((== gCurRoomNum 550) (Print 550 4))
					(else (Print 550 5))
				)
			)
			((Said 'drag,drag,use/log')
				(cond 
					((not (< (ego distanceTo: aLog) 25)) (NotClose))
					((== gCurRoomNum 550) (Print 550 6))
					((!= (aLog x?) 77) (Print 550 7))
					(else (self changeState: 1))
				)
			)
			((Said 'drink') (Print 550 8))
			(
				(or
					(Said 'board,exit,exit,(get<in)/water')
					(Said 'swim,wade')
				)
				(Print 550 9)
			)
			((Said 'get>')
				(cond 
					((Said '/log') (Print 550 10))
					((Said '/palm') (Print 550 11))
					((Said '/bush') (Print 550 12))
				)
			)
			((Said 'look<in/bush') (Print 550 13))
			((Said 'look>')
				(cond 
					((Said '/log')
						(cond 
							((!= (aLog x?) 77) (Print 550 14))
							((== (ego view?) global66) (Print 550 15) (Print 550 16 #at -1 144))
							(else (Print 550 13))
						)
					)
					((Said '/bush') (Print 550 17))
					((Said '/palm') (Print 550 18))
					((Said '[/area,water,creek]') (Print 550 19))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(== (event type?) evMOUSEBUTTON)
						(== gCurRoomNum 550)
					)
					(event claimed: 1)
					(if (== theCursor 992)
						(cond 
							((not (< (ego distanceTo: aLog) 25)) (NotClose))
							((!= (aLog x?) 77) (Print 550 7))
							((== gCurRoomNum 0) (Print 550 6))
							(else (self changeState: 1))
						)
					)
				)
				(if
					(and
						(> (event x?) 0)
						(< (event x?) 319)
						(> (event y?) 21)
						(< (event y?) 108)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 550 19))
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aLog (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((!= (aLog x?) 77) (Print 550 14))
								((== (ego view?) global66) (Print 550 15) (Print 550 16 #at -1 144))
								(else (Print 550 13))
							)
						)
						(995
							(= local78
								(Print
									{What do you want to do?}
									#button
									{Move Log}
									1
									#button
									{Get on Log}
									2
								)
							)
							(switch local78
								(1
									(cond 
										((not (< (ego distanceTo: aLog) 25)) (NotClose))
										((== gCurRoomNum 550) (Print 550 6))
										((!= (aLog x?) 77) (Print 550 7))
										(else (self changeState: 1))
									)
								)
								(2
									(cond 
										((!= gCurRoomNum 0)
											(GoodIdea)
											(if (!= gCurRoomNum 550)
												(Print 550 0)
											else
												(self changeState: 8)
											)
										)
										((not (< (ego distanceTo: aLog) 25)) (NotClose))
										(else
											(self changeState: 4)
											(User canInput: 0 canControl: 0 mapKeyToDir: 0)
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
	)
)

(instance aLog of Prop
	(properties
		y 155
		x 77
		yStep 12
		view 550
		cycleSpeed 2
	)
)

(instance PattiViewer of Code
	(properties)
	
	(method (doit)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) (ego yStep?)))
		(ego brLeft: (- (ego x?) 8))
		(ego brRight: (+ (ego x?) 8))
		(cond 
			((& (ego onControl: 1) $0010) (RoomScript changeState: 14))
			((& (ego onControl:) $0004) (ego view: global66 setCycle: Walk))
			((& (ego onControl: 1) $0002) (ego view: 552 setCycle: Walk))
			((& (ego onControl: 1) $0008) ((Inv at: 17) view: 32) (ego view: 551 setCycle: Fwd))
		)
	)
)

(instance drowningViewer of Code
	(properties)
	
	(method (doit)
		(if (Random 0 5)
			(ego setLoop: (+ drownLoop (Random 0 1)))
		)
	)
)

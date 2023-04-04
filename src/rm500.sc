;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use System)

(public
	rm500 0
	proc500_1 1
	proc500_2 2
)

(local
	[msgBuf 66]
	[titleBuf 22]
	[local88 20] = [-16564 5177 19666 27846 18175 19476 14668 -11668 -14778 -2049 -12039 -6221 -28275 -28200 -29441 -24077 -12441 8987 9137 6655]
	thePic =  500
	bambooRoom
	thirstTimer
)
(procedure (proc500_1 &tmp temp0)
	(curRoom drawPic: thePic)
	(if (proc500_2 (+ 0 bambooRoom))
		(curRoom overlay: (+ thePic 4))
	)
	(if (proc500_2 (+ 80 bambooRoom))
		(curRoom overlay: (+ thePic 2))
	)
	(if (proc500_2 (+ 240 bambooRoom))
		(curRoom overlay: (+ thePic 3))
	)
	(if (proc500_2 (+ 160 bambooRoom))
		(curRoom overlay: (+ thePic 1))
	)
)

(procedure (proc500_2 param1)
	(return
		(if
		(& [local88 (/ param1 16)] (>> $8000 (mod param1 16)))
			1
		else
			0
		)
	)
)

(instance rm500 of Rm
	(properties
		picture 500
		horizon 22
	)
	
	(method (init &tmp temp0)
		(= temp0 500)
		(while (< temp0 510)
			(Load rsPIC temp0)
			(++ temp0)
		)
		(Load rsVIEW 800)
		(Load rsVIEW 501)
		(Load rsVIEW 502)
		(Load rsVIEW 503)
		(if (ego has: 13) (Load rsVIEW ((Inv at: 13) view?)))
		(Load rsSOUND 501)
		(Load rsSOUND 502)
		(Load rsSOUND 503)
		(super init:)
		(gTheMusic number: 500 loop: global72 play:)
		(if (not musicLoop) (= musicLoop 1) (= global66 800))
		(self setScript: RoomScript)
		(if (== prevRoomNum 510)
			(ego posn: 177 26)
			(= bambooRoom 1)
		else
			(ego posn: (Random 130 234) 188)
			(= bambooRoom 68)
		)
		(proc500_1)
		(NormalEgo)
		(ego baseSetter: SteadyBase setCycle: SlowWalk init:)
		(User canInput: 0 canControl: 1 mapKeyToDir: 1)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(= thirstTimer (ego edgeHit?))
			(ego edgeHit: 0 illegalBits: 0)
			(theGame setCursor: waitCursor 1)
			(HandsOff)
			(++ expletiveStr)
			(self changeState: 0)
			(cond 
				((< expletiveStr 8) (ego view: 800 moveSpeed: 0))
				((< expletiveStr 14)
					(ego view: 501 moveSpeed: 0)
					(if (!= 501 (gTheMusic number?)) (gTheMusic fade:))
				)
				((< expletiveStr 17)
					(ego view: 502 moveSpeed: 1)
					(if (!= 502 (gTheMusic number?)) (gTheMusic fade:))
				)
				((< expletiveStr 18)
					(ego view: 503 moveSpeed: 2)
					(if (!= 503 (gTheMusic number?)) (gTheMusic fade:))
				)
				(else (ego view: 503 moveSpeed: 3) (self changeState: 2))
			)
			(switch thirstTimer
				(1
					(if (== bambooRoom 1)
						(gTheMusic fade:)
						(if (not (Btst 37))
							(theGame changeScore: 100)
							(Print 500 0)
							(Print 500 1)
						)
						(curRoom newRoom: 510)
						(return)
					else
						(= bambooRoom (- bambooRoom 8))
					)
				)
				(3
					(if (== bambooRoom 68)
						(curRoom newRoom: 245)
						(return)
					else
						(= bambooRoom (+ bambooRoom 8))
					)
				)
				(2 (++ bambooRoom))
				(4 (-- bambooRoom))
			)
			(if (== thePic 505)
				(= thePic 500)
				(switch thirstTimer
					(1
						(ego posn: (Random 130 234) 187)
					)
					(3 (ego posn: 177 26))
					(2 (ego posn: 1 74))
					(else  (ego posn: 317 74))
				)
			else
				(= thePic 505)
				(switch thirstTimer
					(1
						(ego posn: (Random 80 163) 187)
					)
					(3 (ego posn: 188 26))
					(2 (ego posn: 1 83))
					(else  (ego posn: 314 128))
				)
			)
			(proc500_1)
			(Animate (cast elements?) 0)
			(ego illegalBits: -32768)
			(HandsOn)
			(theGame setCursor: normalCursor (HaveMouse))
			(return)
		)
		(if (== (GameIsRestarting) 2)
			(proc500_1)
			(Animate (cast elements?) 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(cond 
					(
						(and
							(>= expletiveStr 8)
							(<= expletiveStr 13)
							(!= 501 (gTheMusic number?))
						)
						(gTheMusic number: 501 loop: global72 play:)
					)
					(
						(and
							(>= expletiveStr 14)
							(<= expletiveStr 16)
							(!= 502 (gTheMusic number?))
						)
						(gTheMusic number: 502 loop: global72 play:)
					)
					(
						(and
							(<= expletiveStr 18)
							(>= expletiveStr 17)
							(!= 503 (gTheMusic number?))
						)
						(gTheMusic number: 503 loop: global72 play:)
					)
				)
				(cond 
					((== expletiveStr 4) (Print 500 13))
					((== expletiveStr 8) (Print 500 14))
					((== expletiveStr 12) (Print 500 15))
					((== expletiveStr 16)
						(Print 500 16)
						(Print 500 17)
						(Print 500 18)
						(Print 500 19)
					)
				)
			)
			(2 (= seconds 3))
			(3
				(Print 500 20)
				(= seconds 3)
			)
			(4
				(Print 500 21)
				(= seconds 3)
			)
			(5
				(HandsOff)
				(Print 500 22)
				(ego
					illegalBits: 0
					setMotion: 0
					view: 504
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(6
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 505
					register: (Format @msgBuf 500 23)
					next: (Format @titleBuf 500 24)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return (event claimed?)))
		(return
			(cond 
				((Said 'get/bamboo') (Print 500 2))
				((Said 'climb/bamboo') (Print 500 3))
				((Said 'attack/bamboo') (Print 500 4))
				((Said 'nightstand,(get,nightstand<up)') (Print 500 5))
				(
					(or
						(Said 'sip/water')
						(Said 'get/drink<--invalid--')
						(Said 'use,drink,drain/water,beer,bottle')
					)
					(cond 
						((!= gCurRoomNum 0) (GoodIdea))
						((not (ego has: 13)) (DontHave))
						((== ((Inv at: 13) view?) 28) (Print 500 6 #icon 28 0 0))
						(else
							(Ok)
							(theGame changeScore: 20)
							(= expletiveStr 0)
							(gTheMusic number: 500 loop: global72 play:)
							(Print 500 7 #icon 29 0 0)
							(Print 500 8)
							(PutInRoom 13)
							(NormalEgo)
							(ego baseSetter: SteadyBase setCycle: SlowWalk)
							(self changeState: 0)
						)
					)
				)
				((Said 'look>')
					(cond 
						((Said '[/area]') (Print 500 9) (Print 500 10 #at -1 144))
						((Said '/bamboo')
							(Print 500 11)
							(Print (Format @msgBuf 500 12 global92) #at -1 144)
							(++ global92)
						)
					)
				)
			)
		)
	)
)

(instance SteadyBase of Code
	(properties)
	
	(method (doit)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) 2))
		(ego brLeft: (- (ego x?) 10))
		(ego brRight: (+ (ego x?) 10))
	)
)

(instance SlowWalk of Fwd
	(properties)
	
	(method (doit)
		(if
			(or
				(!= (client x?) (client xLast?))
				(!= (client y?) (client yLast?))
			)
			(super doit:)
		)
	)
)

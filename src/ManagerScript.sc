;;; Sierra Script 1.0 - (do not remove this comment)
(script# 421)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use System)

(public
	ManagerScript 0
)

(local
	local0
	[msgBuf 40]
	[titleBuf 22]
)
(instance ManagerScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 4 2)
		(switch (= state newState)
			(0
				(Load rsVIEW 423)
				(Load rsSOUND 6)
				(Load rsSOUND 3)
				(Load rsSOUND 12)
			)
			(1
				(Print 421 0)
				(roomSeconds setCycle: End self)
				(gTheMusic number: 6 loop: -1 play:)
			)
			(2
				(roomSeconds stopUpd:)
				(client setPri: 9 setCycle: Fwd posn: 33 143)
				(= seconds 3)
			)
			(3
				(Print 421 1 #at 10 5 #width 290)
				(Print 421 2 #at -1 144)
				(client loop: 1 cel: 0 setCycle: End self)
			)
			(4
				(ego hide:)
				(client loop: 2 cel: 0 setCycle: End self)
			)
			(5
				(Print 421 3 #at -1 10 #dispose)
				(theGame setSpeed: 1)
				(= seconds 3)
			)
			(6
				(cls)
				(client
					loop: 3
					cycleSpeed: (Random 0 2)
					cel: 0
					setCycle: End self
				)
				(if (>= 10 (++ local0)) (-- state))
			)
			(7
				(client loop: 4 cel: 0 setCycle: End self)
			)
			(8
				(ego
					view: 421
					setLoop: 5
					posn: 60 128
					setStep: 20 9
					setPri: 11
					setMotion: MoveTo 312 68 self
					show:
				)
				(gTheMusic stop: number: 3 loop: 1 play:)
				(client loop: 0 cel: 0 stopUpd:)
				(curRoom north: 0 east: 0)
			)
			(9
				(gTheMusic stop: number: 12 loop: 1 play:)
				(client hide:)
				(roomSeconds setCycle: Beg self)
			)
			(10
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 423
					register: (Format @msgBuf 421 4)
					next: (Format @titleBuf 421 5)
				)
				(self dispose:)
			)
		)
	)
)

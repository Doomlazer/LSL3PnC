;;; Sierra Script 1.0 - (do not remove this comment)
(script# 422)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Follow)
(use Motion)
(use System)

(public
	CherriScript 0
)
(synonyms
	(babe babe dale cheri)
)

(instance CherriScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 10) (> (ego x?) 280)) (self cue:))
		(if
			(and
				(== gameMinutes 2)
				(== (client loop?) 4)
				(== (client x?) 82)
				(== (client y?) 124)
			)
			(switch (Random 0 6)
				(0 (client setCel: 0))
				(1 (client setCycle: Fwd))
			)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 3 2)
		(switch (= state newState)
			(0
				(if (== gameMinutes 3) (self changeState: 3))
				(if (== gameMinutes 1)
					(self changeState: 10)
					(client posn: -20 143 stopUpd:)
				)
			)
			(1
				(Ok)
				(HandsOff)
				(= gCurRoomNum 14)
				(Printf 422 5 global171)
				(client setStep: 0 0 setMotion: Follow ego 222)
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
				(client
					illegalBits: 0
					ignoreActors: 0
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 45 140 self
				)
				(if
					(and
						(> (ego y?) (client y?))
						(< (ego x?) (+ (client x?) 15))
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
				(client setMotion: MoveTo -20 140 self)
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
				(client dispose:)
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
				(client
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
				(client setMotion: MoveTo 82 124 self)
			)
			(15
				(client loop: 4)
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
					((!= (client xLast?) (client x?)) (Print 422 2))
					((not (& (ego onControl:) $0020)) (Print 422 3))
					(else (self changeState: 1))
				)
			)
			((and (== gameMinutes 2) (Said '/babe')) (Print 422 4))
		)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include sci.sh)
(use Main)
(use Motion)
(use Game)
(use Menu)

(public
	rm290 0
)

(local
	doneTime
)
(instance rm290 of Rm
	(properties
		picture 99
		style $0006
	)
	
	(method (init &tmp temp0 [temp1 9])
		(HandsOff)
		(= global97 0)
		(SL disable:)
		(TheMenuBar hide:)
		(super init:)
		(if (!= (= temp0 (FOpen {RESOURCE.LL3} 1)) -1)
			(= global88 (ReadNumber (FGets @temp1 8 temp0)))
		)
		(FClose temp0)
		(ego
			view: 290
			posn: 20 100
			setStep: 1 1
			setMotion: MoveTo 3000 100
			setCycle: Walk
			init:
		)
		(theGame setSpeed: 0)
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ global87) 1)
			(= doneTime (+ 60 (GetTime)))
		)
		(if (< doneTime (GetTime))
			(if global64
				(theGame setSpeed: 2)
			else
				(theGame setSpeed: 6)
			)
			(= filthStr
				(Format
					@requiredLegCurls
					290
					0
					(switch global88
						(4 {Son of a bitch!})
						(3 {Damn it to hell!})
						(2 {Damn!})
						(1 {Gol dang it!})
						(else  {Golly gee!})
					)
				)
			)
			(Format
				@global121
				290
				0
				(switch global88
					(4 {Totally Raunchiest})
					(3 {Really Filthy})
					(2 {Pretty Dirty})
					(1 {Rather Risque})
					(else  {Mother Goose})
				)
			)
			(if (> (DoSound sndFADE) 3)
				(= global72 -1)
			else
				(= global72 1)
			)
			(= global76 0)
			(= global172 5)
			(= global67 0)
			(= global68 0)
			(= global69 0)
			(= orchidMinutes 20)
			(= global66 718)
			(= gCurRoomNum 19)
			(= global82 (Format @global83 290 1))
			(Bclr 4)
			(Bclr 3)
			(TheMenuBar draw:)
			(SL enable:)
			(curRoom newRoom: 200)
		)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 203)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use System)

(public
	rm203 0
)

(local
	[str 30]
	plaqueTextColor
)
(procedure (localproc_000c param1 param2 param3 param4 param5)
	(Display
		param5
		dsCOORD
		param1
		param2
		dsFONT
		param4
		dsCOLOR
		param3
	)
	(Display
		param5
		dsCOORD
		(+ param1 1)
		(+ param2 1)
		dsFONT
		param4
		dsCOLOR
		(- param3 8)
	)
)

(procedure (localproc_0043)
	(localproc_000c
		54
		105
		plaqueTextColor
		1
		(Format @str 203 4)
	)
	(localproc_000c
		90
		120
		plaqueTextColor
		9
		(Format @str 203 5)
	)
	(localproc_000c
		56
		140
		plaqueTextColor
		1
		(Format @str 203 6)
	)
	(localproc_000c
		49
		155
		plaqueTextColor
		1
		(Format @str 203 7)
	)
)

(instance rm203 of Rm
	(properties
		picture 203
	)
	
	(method (init)
		(super init:)
		(Bset 5)
		(= oldStatus currentStatus)
		(= currentStatus curRoomNum)
		(if (> (Graph grGET_COLOURS) 4)
			(= plaqueTextColor 14)
		else
			(= plaqueTextColor 7)
		)
		(User canControl: 0)
		(User canInput: 1)
		(self setScript: RoomScript)
		(localproc_0043)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== (GameIsRestarting) 2) (localproc_0043))
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'look/away,area')
					(Said 'look<done,cease')
					(Said 'done,cease/look')
					(Said 'look<done,cease/awning')
					(Said 'exit,go,exit,done,cease')
				)
				(Ok)
				(= currentStatus oldStatus)
				(curRoom newRoom: 200)
			)
			((Said 'look/awning') (Print 203 0 #at 10 5 #width 290 #mode 1))
			((Said 'look/eye,head') (Print 203 1))
			((Said 'look') (Print 203 2) (if (not playingAsPatti) (Print 203 3)))
		)
	)
)

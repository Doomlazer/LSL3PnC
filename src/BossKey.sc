;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Menu)

(public
	rm90 0
)

(instance rm90 of Rm
	(properties
		picture 90
	)
	
	(method (init)
		(cls)
		(super init:)
		(TheMenuBar hide:)
		(SL disable:)
		(ego hide:)
		(HandsOff)
		(User canInput: 1)
		(Animate 0)
	)
	
	(method (handleEvent event)
		(if
		(or (== (event type?) evMOUSERELEASE) (event claimed?))
			(return)
		)
		(Print
			{Sorry, but from here, all you can do is close the game.}
		)
		(Print 90 1 #at -1 144)
		(event claimed: 1)
	)
)

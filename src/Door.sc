;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)


(class Door of Prop
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 1
		script 0
		cycler 0
		timer 0
		entranceTo 0
		locked 0
		openSnd 0
		closeSnd 0
		doorState 0
		doorCtrl 2
		roomCtrl 4
		doorBlock 16384
		code 0
		illegalBits $0000
		force 0
		notify 0
	)
	
	(method (init)
		(if (and prevRoomNum (== prevRoomNum entranceTo))
			(= doorState 2)
		)
		(if (== doorState 0)
			(= cel 0)
			(ego observeControl: doorBlock)
		else
			(= cel (- (NumCels self) 1))
			(= locked 0)
			(ego ignoreControl: doorBlock)
		)
		(super init:)
		(self stopUpd: ignoreActors:)
	)
	
	(method (doit)
		(super doit:)
		(if (!= doorState 2)
			(ego observeControl: doorBlock)
		else
			(ego ignoreControl: doorBlock)
		)
		(if
		(and roomCtrl entranceTo (& (ego onControl:) roomCtrl))
			(curRoom newRoom: entranceTo)
		)
	)
	
	(method (cue)
		(= doorState (if (== doorState 3) 0 else 2))
		(if (== doorState 2)
			(ego ignoreControl: doorBlock)
		else
			(ego observeControl: doorBlock)
		)
		(self stopUpd:)
		(if notify (notify cue:) (= notify 0))
	)
	
	(method (open)
		(cond 
			(
			(and (not force) (!= (ego onControl: 1) doorCtrl)) (NotClose))
			(locked (Print 50 0))
			((or (== doorState 1) (== doorState 2)) (ItIs))
			(else
				(= doorState 1)
				(self setCycle: End self)
				(if openSnd (openSnd play:))
			)
		)
	)
	
	(method (close)
		(cond 
			(
			(and (not force) (!= (ego onControl: 1) doorCtrl)) (NotClose))
			(locked (Print 50 0))
			((or (== doorState 3) (== doorState 0)) (ItIs))
			((& (ego onControl:) doorBlock) (if (> global88 1) (Print 50 1) else (Print 50 2)))
			(else
				(= doorState 3)
				(self setCycle: Beg self)
				(if closeSnd (closeSnd play:))
			)
		)
	)
)

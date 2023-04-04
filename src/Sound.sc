;;; Sierra Script 1.0 - (do not remove this comment)
(script# 989)
(include sci.sh)
(use Main)
(use System)


(class Sound of Obj
	(properties
		state $0000
		number 0
		priority 0
		loop 1
		handle 0
		signal $0000
		prevSignal 0
		client 0
		owner 0
	)
	
	(method (new param1)
		((super new:) owner: (if argc param1 else 0) yourself:)
	)
	
	(method (init)
		(= signal 0)
		(= prevSignal 0)
		(= state 0)
		(sounds add: self)
		(DoSound sndMASTER_VOLUME self)
	)
	
	(method (play param1 &tmp temp0)
		(self stop:)
		(if (not loop) (= loop 1))
		(self init:)
		(= client (if argc param1 else 0))
		(DoSound sndSET_SOUND self)
	)
	
	(method (playMaybe)
		(self play: &rest)
		(if (== state 2) (self dispose:))
	)
	
	(method (check)
		(if signal
			(if (IsObject client) (client cue: self))
			(= prevSignal signal)
			(= signal 0)
		)
	)
	
	(method (stop param1)
		(if (and argc (not param1)) (= client 0))
		(if handle (DoSound sndSUSPEND handle))
	)
	
	(method (dispose param1)
		(if (and argc (not param1)) (= client 0))
		(sounds delete: self)
		(if handle
			(DoSound sndGET_POLYPHONY handle)
			(= handle 0)
		)
		(super dispose:)
	)
	
	(method (pause param1)
		(DoSound sndINIT param1)
	)
	
	(method (sel_334)
		(theGame sel_333: gVolume)
		(DoSound sndPLAY gVolume)
	)
	
	(method (changeState)
		(DoSound sndSTOP self)
	)
	
	(method (clean param1)
		(if (or (not owner) (== owner param1))
			(self dispose:)
		)
	)
	
	(method (fade param1)
		(if (and argc (not param1)) (= client 0))
		(DoSound sndPAUSE handle)
	)
)

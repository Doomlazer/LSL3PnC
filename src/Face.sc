;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm71 0
	AnimateFace 1
	EgoSays 2
	PersonSays 3
)

(local
	theCycles
	[personSaysBuf 30]
	[nameBuf 5]
	[str 300]
)
(procedure (AnimateFace param1 param2 param3)
	(if (< argc 1) (= param1 0))
	(if (> argc 1)
		(= theCycles param2)
	else
		(= theCycles 22)
	)
	(if (> argc 2) (Print @param3))
	(if (== (RegionScript state?) 0)
		(RegionScript changeState: 1)
	)
	(EyeScript
		changeState:
		(switch param1
			(4 5)
			(7 2)
			(else  3)
		)
	)
	(EyelidScript
		changeState:
			(switch param1
				(1 6)
				(2 6)
				(3 8)
				(6 6)
				(7 6)
				(else  0)
			)
	)
	(NoseScript
		changeState:
			(switch param1
				(2 4)
				(3 4)
				(5 4)
				(6 5)
				(7 5)
				(else  0)
			)
	)
	(MouthScript
		changeState:
			(switch param1
				(2 2)
				(4 5)
				(5 2)
				(3 2)
				(6 1)
				(7 8)
				(else  0)
			)
	)
)

(procedure (EgoSays)
	(Print
		&rest
		#at
		-1
		20
		#title
		{You say...}
		#width
		222
		#mode
		1
	)
)

(procedure (PersonSays)
	(Format @str &rest)
	(if (== (RegionScript state?) 0)
		(Bset 5)
		(RegionScript changeState: 1)
	)
)

(instance rm71 of Rgn
	(properties)
	
	(method (init)
		(Load rsSCRIPT 969)
		(super init:)
		(User canInput: 1 canControl: 1)
		(= str 0)
		(Format
			@personSaysBuf
			(switch curRoomNum
				(455 {Patti says...})
				(265 {Tawni says...})
				(395 {Bambi says...})
				(325 {Suzi says...})
				(335 {Dale says...})
				(435 {Cherri says...})
				(else  {Carlos says...})
			)
		)
		(Format
			@nameBuf
			(switch curRoomNum
				(455 {Patti})
				(265 {Tawni})
				(395 {Bambi})
				(325 {Suzi})
				(335 {Dale})
				(435 {Cherri})
				(else  {Carlos})
			)
		)
		(= currentStatus (theGame setSpeed: 6))
		(if (!= curRoomNum 325)
			(gTheMusic number: curRoomNum loop: global72 play:)
		)
		(self setScript: RegionScript)
		(aEyeWest init:)
		(aEyeEast init:)
		(aEyelidWest init:)
		(aEyelidEast init:)
		(aNose init:)
		(aMouth init:)
	)
	
	(method (newRoom newRoomNumber)
		(theGame setSpeed: currentStatus)
		(if (!= curRoomNum 325) (gTheMusic fade:))
		(super newRoom: newRoomNumber)
	)
	
	(method (notify param1 param2 param3)
		(switch param1
			(1
				(aEyeWest posn: param2 param3)
			)
			(2
				(aEyeEast posn: param2 param3)
			)
			(3
				(aEyelidWest posn: param2 param3)
			)
			(4
				(aEyelidEast posn: param2 param3)
			)
			(5 (aNose posn: param2 param3))
			(6 (aMouth posn: param2 param3))
		)
	)
)

(instance RegionScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1 (= cycles 22))
			(2
				(if str
					(Print
						@str
						#at
						-1
						111
						#title
						@personSaysBuf
						#width
						222
						#mode
						1
					)
					(= str 0)
				)
				(Bclr 5)
				(theGame setCursor: 998 (HaveMouse))
				(= state 0)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(cond 
			((Said 'embrace') (Print 71 0))
			((Said 'eat') (Printf 71 1 global82))
			((Said 'drain/cloth,skirt,dress') (Print 71 2))
			(
				(or
					(Said 'eat,bang/i')
					(Said 'clit,crap,leak,bang,fart,boob,ass,asshole')
				)
				(Printf 71 3 global82)
			)
			((Said '/casino') (EgoSays 71 4) (PersonSays 71 5))
			(
			(Said '/entertainer,maller,bambi,attorney,dale,cheri') (Printf 71 6 @nameBuf))
			((Said 'address') (Printf 71 7 global82))
			((Said '/name') (EgoSays 71 8) (PersonSays 71 9))
		)
	)
)

(instance EyeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 9))
				(if (Random 0 5) (= state 2))
			)
			(1
				(aEyeWest setCycle: End)
				(aEyeEast setCycle: End self)
				(= cycles 0)
				(= state 3)
			)
			(2
				(aEyeWest setCycle: End)
				(aEyeEast setCycle: Beg self)
				(= cycles 0)
				(= state 3)
			)
			(3
				(= cycles 0)
				(aEyeWest
					setCycle:
						CT
						2
						(cond 
							((> 2 (aEyeWest cel?)) 1)
							((< 2 (aEyeWest cel?)) -1)
						)
				)
				(aEyeEast
					setCycle:
						CT
						2
						(cond 
							((> 2 (aEyeEast cel?)) 1)
							((< 2 (aEyeEast cel?)) -1)
						)
						self
				)
			)
			(4 (= cycles theCycles))
			(5
				(aEyeWest setCycle: Beg)
				(aEyeEast setCycle: Beg self)
				(= state -1)
			)
		)
	)
)

(instance EyelidScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 11 33)))
			(1
				(aEyelidWest setCycle: End)
				(aEyelidEast setCycle: End self)
			)
			(2
				(aEyelidWest setCycle: Beg)
				(aEyelidEast setCycle: Beg self)
				(= state -1)
			)
			(3
				(= cycles 0)
				(aEyelidWest setCycle: End self)
				(aEyelidEast setCel: 1)
			)
			(4 (= cycles theCycles))
			(5
				(aEyelidWest setCycle: Beg self)
				(= state -1)
			)
			(6
				(aEyelidWest setCel: 0)
				(aEyelidEast setCel: 0)
				(= cycles theCycles)
				(= state 0)
			)
			(7
				(aEyelidWest setCel: 1)
				(aEyelidEast setCel: 1)
				(= cycles theCycles)
				(= state 0)
			)
			(8
				(aEyelidWest setCel: 2)
				(aEyelidEast setCel: 2)
				(= cycles theCycles)
				(= state 0)
			)
		)
	)
)

(instance NoseScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 22 66)))
			(1
				(aNose setCycle: End self)
				(= cycles 0)
			)
			(2 (= cycles (Random 10 20)))
			(3
				(aNose setCycle: Beg self)
				(= cycles 0)
				(= state -1)
			)
			(4
				(aNose setCycle: End)
				(= cycles theCycles)
				(= state 2)
			)
			(5
				(aNose setCycle: Beg)
				(= cycles theCycles)
				(= state 0)
			)
		)
	)
)

(instance MouthScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aMouth setLoop: 6 setCel: 0)
				(= cycles 0)
			)
			(1
				(aMouth setLoop: 5 cel: 0 setCycle: End self)
				(= cycles 0)
				(= state -1)
			)
			(2
				(aMouth setLoop: 6 cel: 0 setCycle: End self)
				(= cycles 0)
			)
			(3 (= cycles theCycles))
			(4
				(aMouth setCycle: Beg self)
				(= state -1)
			)
			(5
				(aMouth setLoop: 7 cel: 0 setCycle: End self)
				(= cycles 0)
			)
			(6 (= cycles 10))
			(7
				(aMouth setCycle: Beg self)
				(= state -1)
			)
			(8
				(aMouth setLoop: 8 cel: 0 setCycle: End self)
				(= cycles 0)
				(= state -1)
			)
		)
	)
)

(instance aEyeWest of Prop
	(properties)
	
	(method (init)
		(self view: curRoomNum setPri: 2 setScript: EyeScript)
		(super init:)
	)
)

(instance aEyeEast of Prop
	(properties
		loop 1
	)
	
	(method (init)
		(self view: curRoomNum setPri: 3)
		(super init:)
	)
)

(instance aEyelidWest of Prop
	(properties
		loop 2
	)
	
	(method (init)
		(self
			view: curRoomNum
			ignoreActors:
			setPri: 5
			setScript: EyelidScript
		)
		(super init:)
	)
)

(instance aEyelidEast of Prop
	(properties
		loop 3
	)
	
	(method (init)
		(self view: curRoomNum ignoreActors: setPri: 6)
		(super init:)
	)
)

(instance aNose of Prop
	(properties
		loop 4
	)
	
	(method (init)
		(self
			view: curRoomNum
			cycleSpeed: 2
			setPri: 9
			setScript: NoseScript
		)
		(super init:)
	)
)

(instance aMouth of Prop
	(properties
		loop 6
	)
	
	(method (init)
		(self
			view: curRoomNum
			cycleSpeed: 1
			setPri: 10
			setScript: MouthScript
		)
		(super init:)
	)
)

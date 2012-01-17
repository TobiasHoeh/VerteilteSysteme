class Reader {
	def act() = {
	  val random = new Random()
	  receiveWithin(random.nextInt(1000))
	}
}

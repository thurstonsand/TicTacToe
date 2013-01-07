package sand

// Got this from the web at https://github.com/jimm/scala/blob/master/StopWatch.scala

class StopWatcha(val name:String = null) {

  var t0 = 0L
  var elapsedMillis = 0L
  var numStarts = 0

  def start {
    numStarts += 1
    elapsedMillis = 0
    t0 = System.currentTimeMillis()
  }

  def pause {
    elapsedMillis += System.currentTimeMillis() - t0
    t0 = 0
  }

  def resume {
    numStarts += 1
    t0 = System.currentTimeMillis()
  }

  // Returns total elapsed time
  def elapsedTime = elapsedMillis + (System.currentTimeMillis() - t0)

  def mark(printName: Boolean = true) = stop(printName)

  def stop(printName: Boolean = true) {
    val now = System.currentTimeMillis()
    var total = elapsedMillis
    if (t0 != 0)
      total += now - t0

    if (numStarts == 0)
      System.err.println("(warning: StopWatch " +
        (if (name != null) name else "(unnamed)") +
        " was never started)")
    System.err.print((if (printName && name != null) name + ": " else "") +
      (total / 1000.0) + " seconds")
    System.err.println
  }
}

//val s = new StopWatch("My Name")
//s.start
//Thread.sleep(1750)
//s.stop()

//
//   Stopwatch for benchmarking
//
class Stopwatch {

  private var startTime = -1L
  private var stopTime = -1L
  private var running = false

  def start(): Stopwatch = {
    startTime = System.currentTimeMillis()
    running = true
    this
  }

  def stop(): Stopwatch = {
    stopTime = System.currentTimeMillis()
    running = false
    this
  }

  def isRunning(): Boolean = running

  def getElapsedTime() = {
    if (startTime == -1) {
      0
    }
    if (running) {
      System.currentTimeMillis() - startTime
    }
    else {
      stopTime - startTime
    }
  }

  def reset() {
    startTime = -1
    stopTime = -1
    running = false
  }
}
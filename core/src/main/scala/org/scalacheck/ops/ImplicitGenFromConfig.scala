package org.scalacheck.ops

import org.scalacheck.Gen

object GenFromConfig extends ImplicitGenFromConfig

class GenFromConfig[T](val gen: Gen[T]) extends AnyVal {

  def instance(implicit config: GenConfig): T = {
    gen.pureApply(config.params, config.seed, config.retries)
  }
}

trait ImplicitGenFromConfig {

  import scala.language.implicitConversions
  implicit def genWithConfig[T](gen: Gen[T]): GenFromConfig[T] = new GenFromConfig[T](gen)
}

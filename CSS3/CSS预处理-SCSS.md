[toc]

# SCSS





~~~ scss
@mixin mix {
  border: 1px solid red;
}

body {
  p {
    @include mix;
    color: red;
    &:hover {
      color: green;
    }
  }
}
~~~

~~~ scss
@mixin mix($width) {
  border: $width solid red;
}

body {
  p {
    @include mix(2px);
    color: red;
    &:hover {
      color: green;
      @include mix(6px);
    }
  }
}
~~~


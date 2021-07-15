<html><body>
<pre style="margin: 0em;background: #ededed"><span style="color: #0073ff; font-weight: bold;">  1  </span><span style="font-family: monospace"></span><span style="color: #cc0066;">import</span> java.util.Arrays;</pre>
<pre style="margin: 0em;background: #ededed"><span style="color: #0073ff; font-weight: bold;">  2  </span><span style="font-family: monospace"></span><span style="color: #cc0066;">import</span> java.util.Scanner;</pre>
<pre style="margin: 0em;background: #ededed"><span style="color: #0073ff; font-weight: bold;">  3  </span><span style="font-family: monospace"></span></pre>
<pre style="margin: 0em;background: #ededed"><span style="color: #0073ff; font-weight: bold;">  4  </span><span style="font-family: monospace">/**</span><span style="color: #0073ff; font-family: serif; "></span></pre>
<pre style="margin: 0em;background: #ededed"><span style="color: #0073ff; font-weight: bold;">  5  </span><span style="font-family: monospace">   </span><span style="color: #0073ff; font-family: serif; ">This program demonstrates the binary search algorithm.</span></pre>
<pre style="margin: 0em;background: #ededed"><span style="color: #0073ff; font-weight: bold;">  6  </span><span style="font-family: monospace"></span><span style="color: #0073ff; font-family: serif; "></span><span style="font-family: monospace">*/</span></pre>
<pre style="margin: 0em;background: #ededed"><span style="color: #0073ff; font-weight: bold;">  7  </span><span style="font-family: monospace"></span><span style="color: #cc0066;">public</span> <span style="color: #cc0066;">class</span> BinarySearchDemo</pre>
<pre style="margin: 0em;background: #ededed"><span style="color: #0073ff; font-weight: bold;">  8  </span><span style="font-family: monospace">{  </span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;">  9  </span></span><span style="font-family: monospace">   </span><span style="color: #cc0066;">public</span> <span style="color: #cc0066;">static</span> <span style="color: #cc0066;">void</span> main(String[] args)</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 10  </span></span><span style="font-family: monospace">   {  </span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 11  </span></span><span style="font-family: monospace">      //</span><span style="color: #0073ff; font-family: serif; "> Construct random array</span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 12  </span></span><span style="font-family: monospace">   </span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 13  </span></span><span style="font-family: monospace">      </span><span style="color: #cc0066;">int</span>[] a = ArrayUtil.randomIntArray(<span style="color: #66ff19;">20</span>, <span style="color: #66ff19;">100</span>);</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 14  </span></span><span style="font-family: monospace">      </span>Arrays.sort(a);</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 15  </span></span><span style="font-family: monospace">      </span>System.out.println(Arrays.toString(a));</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 16  </span></span><span style="font-family: monospace">      </span>BinarySearcher searcher = <span style="color: #cc0066;">new</span> BinarySearcher(a);</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 17  </span></span><span style="font-family: monospace">      </span>Scanner in = <span style="color: #cc0066;">new</span> Scanner(System.in);</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 18  </span></span><span style="font-family: monospace"></span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 19  </span></span><span style="font-family: monospace">      </span><span style="color: #cc0066;">boolean</span> done = false;</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 20  </span></span><span style="font-family: monospace">      </span><span style="color: #cc0066;">while</span> (!done)</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 21  </span></span><span style="font-family: monospace">      {</span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 22  </span></span><span style="font-family: monospace">         </span>System.out.print</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 23  </span></span><span style="font-family: monospace">               (</span><span style="color: #32e598;">&quot;Enter number to search for, -1 to quit:&quot;</span>);</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 24  </span></span><span style="font-family: monospace">         </span><span style="color: #cc0066;">int</span> n = in.nextInt();</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 25  </span></span><span style="font-family: monospace">         </span><span style="color: #cc0066;">if</span> (n == <span style="color: #66ff19;">-1</span>) </pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 26  </span></span><span style="font-family: monospace">            </span>done = true;</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 27  </span></span><span style="font-family: monospace">         </span><span style="color: #cc0066;">else</span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 28  </span></span><span style="font-family: monospace">         {</span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 29  </span></span><span style="font-family: monospace">            </span><span style="color: #cc0066;">int</span> pos = searcher.search(n);</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 30  </span></span><span style="font-family: monospace">            </span>System.out.println(<span style="color: #32e598;">&quot;Found in position &quot;</span> + pos);</pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 31  </span></span><span style="font-family: monospace">         }</span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 32  </span></span><span style="font-family: monospace">      }</span></pre>
<pre style="margin: 0em;"><span style="background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 33  </span></span><span style="font-family: monospace">   }</span></pre>
<pre style="margin: 0em;background: #ededed"><span style="color: #0073ff; font-weight: bold;"> 34  </span><span style="font-family: monospace">}</span></pre>
</body></html>
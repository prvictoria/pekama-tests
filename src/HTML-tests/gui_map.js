/**
 * Created by slava on 28.06.2016.
 */
var gui_map = {};

var gui_map = {

    main:   { 
          lnk_logout:             "link=Logout"
        , lnk_customer_section:   "link=Customers"
        , lnk_jobs_section:       "link=Jobs"
        , lnk_my_items_section:   "link=My Items"

        , quicksearch: { p_topic: "_ctl0:_ctl3:topics_"
            , keyword: "_ctl0:_ctl3:keyword_"
            , btn_go:  "_ctl0:_ctl3:go_"
        }
        , recents: { lnk_clear:        "link=[clear]"
            , lnk_fn_link_id:   function( x ) { return "_ctl0__ctl7_rpt_recents__ctl" + (x + 1) + "__ctl0_v_jump" }
        }
    }

    // etc.......................
};
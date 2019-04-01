import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Button from "@material-ui/core/Button";
import Logo from "../images/logo_transparent.png";

const styles = theme => ({
  root: {
    flexGrow: 1
  },
  appBar: {
    backgroundColor: "white"
  },
  grow: {
    flex: 1
  },
  signIn: {
    "margin-left": "auto"
  },
  img: {
    height: "80px"
  },
  signUp: {
    marginLeft: theme.spacing.unit
  }
});

function Navbar(props) {
  const { classes } = props;
  return (
    <div className={classes.root}>
      <AppBar position="static" className={classes.appBar}>
        <Toolbar>
          <a href="/">
            <img
              className={classes.img}
              alt=""
              src={Logo}
              href="/ProjectBoard"
            />
          </a>
          <Button
            href="/SignIn"
            className={classes.signIn}
            color="inherit"
            border={1}
          >
            Sign In
          </Button>
          <Button
            variant="contained"
            href="/SignUp"
            color="secondary"
            className={classes.signUp}
          >
            Sign up
          </Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}

Navbar.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Navbar);

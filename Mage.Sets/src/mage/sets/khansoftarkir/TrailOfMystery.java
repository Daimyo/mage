/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.khansoftarkir;

import java.util.UUID;
import mage.abilities.common.EntersBattlefieldControlledTriggeredAbility;
import mage.abilities.common.TurnedFaceUpAllTriggeredAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.filter.common.FilterBasicLandCard;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.other.FaceDownPredicate;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author LevelX2
 */
public class TrailOfMystery extends CardImpl {

    private static final FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent("a face-down creature");

    static {
        filter.add(new FaceDownPredicate());
    }

    public TrailOfMystery(UUID ownerId) {
        super(ownerId, 154, "Trail of Mystery", Rarity.RARE, new CardType[]{CardType.ENCHANTMENT}, "{1}{G}");
        this.expansionSetCode = "KTK";

        // Whenever a face-down creature enters the battlefield under your control, you may search your library for a basic land card, reveal it, put it into your hand, then shuffle your library.
        Effect effect = new SearchLibraryPutInHandEffect(new TargetCardInLibrary(0,1, new FilterBasicLandCard()), true, true);
        this.addAbility(new EntersBattlefieldControlledTriggeredAbility(Zone.BATTLEFIELD, effect, filter, true));
        
        // Whenever a permanent you control is turned face up, if it's a creature, it gets +2/+2 until end of turn.
        this.addAbility(new TrailOfMysteryTriggeredAbility());
    }

    public TrailOfMystery(final TrailOfMystery card) {
        super(card);
    }

    @Override
    public TrailOfMystery copy() {
        return new TrailOfMystery(this);
    }
}

class TrailOfMysteryTriggeredAbility extends TurnedFaceUpAllTriggeredAbility {

    public TrailOfMysteryTriggeredAbility() {
        super(new BoostTargetEffect(2,2,Duration.EndOfTurn), new FilterControlledCreaturePermanent(), true);
    }

    public TrailOfMysteryTriggeredAbility(final TrailOfMysteryTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public TrailOfMysteryTriggeredAbility copy() {
        return new TrailOfMysteryTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "Whenever a permanent you control is turned face up, if it's a creature, it gets +2/+2 until end of turn.";
    }
}
